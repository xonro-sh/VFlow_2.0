package com.xonro.vflow.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xonro.vflow.bases.bean.BaseResponse;
import com.xonro.vflow.bases.bean.WechatConf;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.bases.helper.RequestExecutor;
import com.xonro.vflow.bases.service.WechatConfService;
import com.xonro.vflow.wechat.bean.CreateQrCode;
import com.xonro.vflow.wechat.bean.QrCode;
import com.xonro.vflow.wechat.bean.message.Message;
import com.xonro.vflow.wechat.dao.MessageRepository;
import com.xonro.vflow.wechat.helper.UrlBuilder;
import com.xonro.vflow.wechat.service.WechatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author louie
 * @date 2018-1-12
 */
@Service
public class WechatServiceImpl implements WechatService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UrlBuilder urlBuilder;

    private final WechatConfService wechatConfService;

    private final MessageRepository messageRepository;

    @Autowired
    public WechatServiceImpl(MessageRepository messageRepository, UrlBuilder urlBuilder, WechatConfService wechatConfService) {
        this.messageRepository = messageRepository;
        this.urlBuilder = urlBuilder;
        this.wechatConfService = wechatConfService;
    }

    @Override
    public QrCode createQrCode(Long expireSeconds, Object sceneValue) throws IOException, VFlowException {
        try {
            CreateQrCode createQrCode = new CreateQrCode(expireSeconds,sceneValue);
            // 1、获取含有参数二维码
            JSONObject jsonObject = new RequestExecutor(urlBuilder.buildCreateQrCodeUrl())
                    .execute(JSON.toJSONString(createQrCode))
                    .getResponseAsObject(JSONObject.class);
            String ticket = jsonObject.getString("ticket");
            long reExpireSeconds = jsonObject.getLong("expire_seconds");
            String url = jsonObject.getString("url");

            // 2、通过ticket换取二维码图片
            return new QrCode(
                    reExpireSeconds,System.currentTimeMillis()/1000,url,
                    new RequestExecutor(urlBuilder.buildQrCodeImageUrl(ticket)).downloadFile()
            );
        } catch (IOException | VFlowException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public byte[] getQrCodeImage(String ticket) {
        return new byte[0];
    }



    /**
     * 更新微信服务号配置
     *
     * @param wechatConf 服务号配置实体
     * @return 结果
     */
    @Override
    public BaseResponse updateServiceNoConf(WechatConf wechatConf) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setOk(true);
        try {
            wechatConfService.saveWechatConf(wechatConf);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }

    /**
     * 获取微信服务号配置
     *
     * @return 结果
     */
    @Override
    public BaseResponse getServiceNoConf() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setOk(true);
        try {
            baseResponse.setData(wechatConfService.getWechatConf());
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }

    /**
     * 获取缓存的公众号配置
     *
     * @return
     */
    @Override
    public WechatConf getConfFromCache(){
        return wechatConfService.getWechatConf();
    }

    /**
     * 获取消息配置
     * @param type 消息类型（大类）
     * @return 结果
     */
    @Override
    public BaseResponse getMessageConf(String type) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setOk(true);
        try {
            List<Message> messages = messageRepository.findByType(type);
            baseResponse.setData(null);
            if (messages.size()!=0){
                baseResponse.setData(messages.get(0));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }

    /**
     * 更新消息设置
     * @param message 信息对象
     * @return 结果
     */
    @Override
    public BaseResponse updateMessageConf(Message message) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setOk(true);
        try {
            messageRepository.save(message);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }

}
