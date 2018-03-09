package com.xonro.vflow.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.bases.helper.RequestExecutor;
import com.xonro.vflow.wechat.bean.material.Article;
import com.xonro.vflow.wechat.bean.material.MaterialCountResult;
import com.xonro.vflow.wechat.bean.material.MaterialResult;
import com.xonro.vflow.wechat.bean.material.NewsResponse;
import com.xonro.vflow.wechat.enums.WechatEnums;
import com.xonro.vflow.wechat.helper.UrlBuilder;
import com.xonro.vflow.wechat.service.MaterialService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Alex
 * @date 2018/1/17
 */
@Service
public class MaterialServiceImpl implements MaterialService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UrlBuilder urlBuilder;

    @Autowired
    public MaterialServiceImpl(UrlBuilder urlBuilder) {
        this.urlBuilder = urlBuilder;
    }

    @Override
    public MaterialResult uploadTemMedia(@NotNull File file, @NotNull String type) throws VFlowException, IOException {
        try {
            return new RequestExecutor(urlBuilder.buildUploadTemMediaUrl(type))
                    .upload(file.getName(), FileUtils.readFileToByteArray(file))
                    .getResponseAsObject(MaterialResult.class);
        } catch (VFlowException | IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    /**
     * 获取临时素材接口
     *
     * @param mediaId 媒体文件ID
     * @return 返回结果
     * @throws IOException 异常
     */
    @Override
    public byte[] getTemMedia(@NotNull String mediaId) throws IOException {
        byte[] materialResult;
        try {
            materialResult = new RequestExecutor(urlBuilder.buildGetTemMediaUrl(mediaId))
                    .downloadFile();
            return materialResult;
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    /**
     * 新增永久图文素材接口
     *
     * @param newsResponse 图文素材请求数据模型
     * @return 返回media_id
     * @throws IOException 异常
     * @throws VFlowException 异常
     */
    @Override
    public MaterialResult addNews(@NotNull NewsResponse newsResponse) throws IOException, VFlowException {
        MaterialResult materialResult;
        try {
            materialResult = new RequestExecutor(urlBuilder.buildMaterialAddNewsUrl())
                    .execute(JSON.toJSONString(newsResponse))
                    .getResponseAsObject(MaterialResult.class);
            return materialResult;
        } catch (IOException | VFlowException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    /**
     * 上传图文消息内的图片获取URL
     *
     * @param file 上传的文件
     * @return url, 可放置图文消息中使用
     * @throws IOException 异常
     * @throws VFlowException 异常
     */
    @Override
    public MaterialResult uploadImg(@NotNull File file) throws IOException, VFlowException {
        try {
            return new RequestExecutor(urlBuilder.buildUpLoadImg())
                    .upload(file.getName(), FileUtils.readFileToByteArray(file))
                    .getResponseAsObject(MaterialResult.class);
        } catch (VFlowException | IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    /**
     * 新增永久视频素材
     *
     * @param file         上传的文件
     * @param title        视频素材的标题
     * @param introduction 视频素材的描述
     * @return MaterialResult
     */
    @Override
    public MaterialResult addVideo(@NotNull File file, @NotNull String title, @NotNull String introduction) throws IOException, VFlowException {
        //提交的参数
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("title", title);
        dataParams.put("introduction", introduction);
        Map<String, String> dataMap = new HashMap<>(1);
        dataMap.put("description", JSON.toJSONString(dataParams));
        MaterialResult materialResult;
        try {
            materialResult = new RequestExecutor(urlBuilder.buildMaterialAddUrl(WechatEnums.MATERIAL_VIDEO.getValue())).
                    uploadVideo(file.getName(), FileUtils.readFileToByteArray(file), dataMap).
                    getResponseAsObject(MaterialResult.class);
            return materialResult;
        } catch (IOException | VFlowException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    /**
     * 新增其他永久素材
     *
     * @param file 上传的文件
     * @param type 媒体文件类型，分别有图片（image）、语音（voice）和缩略图（thumb）
     * @return MaterialResult
     */
    @Override
    public MaterialResult addOthers(@NotNull File file, @NotNull String type) throws IOException, VFlowException {
        try {
            return new RequestExecutor(urlBuilder.buildMaterialAddUrl(type))
                    .upload(file.getName(), FileUtils.readFileToByteArray(file))
                    .getResponseAsObject(MaterialResult.class);
        } catch (VFlowException | IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public MaterialResult delete(@NotNull String mediaId) throws IOException, VFlowException {
        //提交的参数
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("media_id", mediaId);
        MaterialResult materialResult;
        try {
            materialResult = new RequestExecutor(urlBuilder.buildMaterialDelUrl())
                    .execute(JSON.toJSONString(dataParams))
                    .getResponseAsObject(MaterialResult.class);
            return materialResult;
        } catch (IOException | VFlowException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public MaterialResult updateNews(@NotNull String mediaId, @NotNull String index, @NotNull Article article) throws IOException, VFlowException {
        //提交的参数
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("media_id", mediaId);
        dataParams.put("index", mediaId);
        dataParams.put("articles", article);
        MaterialResult materialResult;
        try {
            materialResult = new RequestExecutor(urlBuilder.buildMaterialUpdateNewsUrl())
                    .execute(JSON.toJSONString(dataParams))
                    .getResponseAsObject(MaterialResult.class);
            return materialResult;
        } catch (IOException | VFlowException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public MaterialCountResult getMaterialCount() throws IOException, VFlowException {
        MaterialCountResult materialCountResult;
        try {
            materialCountResult = new RequestExecutor(urlBuilder.buildMaterialCountUrl())
                    .execute()
                    .getResponseAsObject(MaterialCountResult.class);
            return materialCountResult;
        } catch (IOException | VFlowException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public String getMaterialBatch(@NotNull String type, @NotNull Integer offset, @NotNull Integer count) throws IOException, VFlowException {
        //提交的参数
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("type", type);
        dataParams.put("offset", offset);
        dataParams.put("count", count);
        String result;
        try {
            result = new RequestExecutor(urlBuilder.buildGetMaterialBatch())
                    .execute(JSON.toJSONString(dataParams))
                    .getResponseAsObject(String.class);
            return result;
        } catch (IOException | VFlowException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }
}
