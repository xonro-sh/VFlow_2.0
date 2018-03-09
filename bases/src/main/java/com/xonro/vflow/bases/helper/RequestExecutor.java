package com.xonro.vflow.bases.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xonro.vflow.bases.exception.VFlowException;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * @author louie
 * @Description: 请求执行器
 * @date 2018-3-1 19:02
 */
public class RequestExecutor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Charset CHARSET = Charset.forName("UTF-8");

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 请求响应的json结果
     */
    private String response;

    public RequestExecutor(String requestUrl){
        this.requestUrl = requestUrl;
    }

    /**
     * 执行http请求
     * @param requestData 请求参数，可为空，为空时执行get请求，非空时执行post请求
     * @return 执行结果
     * @throws IOException
     */
    public RequestExecutor execute(String ... requestData) throws IOException, VFlowException {
        String response = "";

        try {
            //有请求参数，执行post请求
            if (requestData != null && requestData.length > 0){
                String data = requestData[0];
                response = Request.Post(requestUrl).connectTimeout(3000).socketTimeout(3000)
                        .bodyString(data, ContentType.APPLICATION_JSON)
                        .execute()
                        .returnContent().asString(CHARSET);
            }
            //无请求参数，执行Get请求
            else {
                response = Request.Get(requestUrl).connectTimeout(3000).socketTimeout(3000)
                        .execute().returnContent().asString(CHARSET);
            }

            if (validateResult(response)){
                this.response = response;
                return this;
            }
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }catch (VFlowException e){
            logger.error(e.getMessage(),e);
            throw e;
        }
        return null;
    }

    /**
     * 下载文件
     * @return 返回下载文件的字节流数据
     * @throws IOException
     */
    public byte[] downloadFile() throws IOException {
        return Request.Get(requestUrl).connectTimeout(3000).socketTimeout(3000)
                .execute().returnContent().asBytes();
    }

    /**
     * 上传文件
     * @param fileName 文件名称
     * @param mediaData 文件字节码数组
     * @return 上传结果
     * @throws IOException
     * @throws VFlowException
     */
    public RequestExecutor upload(String fileName,byte[] mediaData) throws IOException, VFlowException {
        try {
            HttpEntity httpEntity = MultipartEntityBuilder.create()
                    .addBinaryBody("media",mediaData,ContentType.DEFAULT_BINARY,fileName)
                    .build();

            String response = Request.Post(requestUrl)
                    .body(httpEntity)
                    .execute()
                    .returnContent().asString(CHARSET);

            if (validateResult(response)){
                this.response = response;
                return this;
            }
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        } catch (VFlowException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
        return null;
    }

    /**
     * 上传视频文件
     * @param fileName 文件名称
     * @param mediaData 文件字节码数组
     * @param map 视频素材的标题以及描述
     * @return 上传结果
     * @throws IOException
     * @throws VFlowException
     */
    public RequestExecutor uploadVideo(String fileName, byte[] mediaData, Map<String, String> map) throws IOException, VFlowException {
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create()
                    .addBinaryBody("media",mediaData,ContentType.DEFAULT_BINARY,fileName);
            for (String key: map.keySet()){
                StringBody stringBody = new StringBody(map.get(key), ContentType.DEFAULT_TEXT);
                builder.addPart(key, stringBody);
            }
            HttpEntity httpEntity = builder.build();

            String response = Request.Post(requestUrl)
                    .body(httpEntity)
                    .execute()
                    .returnContent().asString(CHARSET);

            if (validateResult(response)){
                this.response = response;
                return this;
            }
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        } catch (VFlowException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
        return null;
    }

    /**
     * 提交form数据
     * @param formFiles form文件数据，key：文件名称，value：文件字节码数组
     * @param formParams form参数，key：参数名称，value：参数值
     * @return
     */
    public RequestExecutor submitFormData(Map<String,byte[]> formFiles,Map<String,String> formParams) throws IOException, VFlowException {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        for(String fileName : formFiles.keySet()){
            builder.addBinaryBody("media",formFiles.get(fileName),ContentType.DEFAULT_BINARY,fileName);
        }

        for(String paramName : formParams.keySet()){
            builder.addTextBody(paramName,formParams.get(paramName),ContentType.DEFAULT_TEXT);
        }

        try {
            String response = Request.Post(requestUrl)
                    .body(builder.build())
                    .execute()
                    .returnContent()
                    .asString(CHARSET);
            if (validateResult(response)){
                this.response = response;
            }

        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }catch (VFlowException e){
            logger.error(e.getMessage(),e);
            throw e;
        }
        return this;
    }

    /**
     * 将响应信息转为对象实例
     * @param objectClass 期望获取的实例类型
     * @param <T>
     * @return 转换后的对象实例
     */
    public <T>T getResponseAsObject(Class<T> objectClass){
        return JSON.parseObject(response,objectClass);
    }

    /**
     * 将响应信息转为对象实例集合
     * @param objectClass 期望获取的实例类型
     * @param <T>
     * @return 转换后的对象实例集合
     */
    public <T>List<T> getResponseAsList(Class<T> objectClass){
        return JSON.parseArray(response,objectClass);
    }

    /**
     * 校验微信请求是否获取正确响应
     * @param result 微信的响应信息
     * @return true正确相应 false响应失败
     */
    private boolean validateResult(String result) throws VFlowException {
        JSONObject resultJson = JSONObject.parseObject(result);
        String errorCode = resultJson.getString("errcode");
        if (StringUtils.isEmpty(errorCode) || "0".equals(errorCode.trim())){
            return true;
        }
        logger.error("访问微信失败，url："+requestUrl+",错误信息："+result);
        throw new VFlowException(errorCode,resultJson.getString("errmsg"));
    }

}
