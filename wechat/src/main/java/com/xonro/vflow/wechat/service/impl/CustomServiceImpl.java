package com.xonro.vflow.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.bases.helper.RequestExecutor;
import com.xonro.vflow.wechat.bean.custom.CustomInfo;
import com.xonro.vflow.wechat.bean.custom.CustomMessageMain;
import com.xonro.vflow.wechat.bean.custom.CustomServiceResult;
import com.xonro.vflow.wechat.helper.UrlBuilder;
import com.xonro.vflow.wechat.service.CustomService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

/**
 * @author Alex
 * @date 2018/1/10
 */
@Service
public class CustomServiceImpl implements CustomService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UrlBuilder urlBuilder;

    @Autowired
    public CustomServiceImpl(UrlBuilder urlBuilder) {
        this.urlBuilder = urlBuilder;
    }

    @Override
    public CustomServiceResult addCustom(String kfAccount, String nickName, String password) throws IOException, VFlowException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("kf_account", kfAccount);
        jsonObject.put("nickname", nickName);
        jsonObject.put("password", StringUtils.isEmpty(password)?"": DigestUtils.md5Hex(password));
        CustomServiceResult customServiceResult;
        try {
            customServiceResult = new RequestExecutor(urlBuilder.buildCustomServiceAdd())
                    .execute(jsonObject.toJSONString())
                    .getResponseAsObject(CustomServiceResult.class);
            return customServiceResult;
        } catch (VFlowException | IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public CustomServiceResult updateCustom(@NotNull String kfAccount, @NotNull String nickName, String password) throws IOException, VFlowException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("kf_account", kfAccount);
        jsonObject.put("nickname", nickName);
        jsonObject.put("password", StringUtils.isEmpty(password)?"": DigestUtils.md5Hex(password));
        CustomServiceResult customServiceResult;
        try {
            customServiceResult = new RequestExecutor(urlBuilder.buildCustomServiceUpdate())
                    .execute(jsonObject.toJSONString())
                    .getResponseAsObject(CustomServiceResult.class);
            return customServiceResult;
        } catch (VFlowException | IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    /**
     * 删除客服账号
     *
     * @param kfAccount 完整的客服账号 格式为：账号前缀@公众号微信号
     * @param nickName  客服昵称
     * @param password  客服账号登录密码，该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码
     * @return 删除客服是否成功
     */
    @Override
    public CustomServiceResult delCustom(@NotNull String kfAccount, String nickName, String password) throws IOException, VFlowException {
        CustomServiceResult customServiceResult;
        try {
            customServiceResult = new RequestExecutor(urlBuilder.buildCustomServiceDel(kfAccount, nickName, password)).execute().getResponseAsObject(CustomServiceResult.class);
            return customServiceResult;
        } catch (VFlowException | IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public CustomServiceResult uploadHeadImg(String title,byte[] head,String kfAccount) throws IOException, VFlowException {
        try {
            return new RequestExecutor(urlBuilder.buildCustomServiceHeadImg(kfAccount))
                    .upload(title, head)
                    .getResponseAsObject(CustomServiceResult.class);
        } catch (VFlowException | IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public List<CustomInfo> getKfList() throws IOException, VFlowException {
        try {
            String result = new RequestExecutor(urlBuilder.buildGetKfList()).execute().getResponseAsObject(String.class);
            JSONObject json = JSON.parseObject(result);
            return JSON.parseArray(json.getString("kf_list"), CustomInfo.class);
        } catch (VFlowException | IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public CustomServiceResult customSend(CustomMessageMain customMessageMain) throws IOException, VFlowException {
        CustomServiceResult customServiceResult;
        try {
            customServiceResult = new RequestExecutor(urlBuilder.buildCustomSend()).
                    execute(JSON.toJSONString(customMessageMain))
                    .getResponseAsObject(CustomServiceResult.class);
            return customServiceResult;
        } catch (VFlowException | IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public CustomServiceResult customTyping(String toUser, String typing) throws IOException, VFlowException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser", toUser);
        jsonObject.put("command", typing);
        CustomServiceResult customServiceResult;
        try {
            customServiceResult = new RequestExecutor(urlBuilder.buildCustomTyping())
                    .execute(jsonObject.toJSONString())
                    .getResponseAsObject(CustomServiceResult.class);
            return customServiceResult;
        } catch (VFlowException | IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

}
