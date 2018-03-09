package com.xonro.vflow.wechat.service.impl;

import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.bases.helper.RequestExecutor;
import com.xonro.vflow.wechat.bean.WechatJsApiTicket;
import com.xonro.vflow.wechat.bean.WechatJsSignature;
import com.xonro.vflow.wechat.helper.UrlBuilder;
import com.xonro.vflow.wechat.service.JsApiService;
import com.xonro.vflow.wechat.service.WechatService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLDecoder;

/**
 * @author Alex
 * @date 2018/1/9
 */
@Service
public class JsApiServiceImpl implements JsApiService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UrlBuilder urlBuilder;

    private final
    WechatService wechatService;

    private WechatJsApiTicket jsApiTicket;

    @Autowired
    public JsApiServiceImpl(UrlBuilder urlBuilder, WechatService wechatService) {
        this.urlBuilder = urlBuilder;
        this.wechatService = wechatService;
    }

    /**
     * 获取微信公众平台jsapi_ticket临时票据
     * @return  jsapi_ticket
     */
    @Override
    public WechatJsApiTicket getJsApiTicket(){
        String url = urlBuilder.buildJsApiTicketUrl();
        try {
            jsApiTicket = new RequestExecutor(url).execute().getResponseAsObject(WechatJsApiTicket.class);
            jsApiTicket.setAccessTime(System.currentTimeMillis()/1000);
            return jsApiTicket;
        } catch (VFlowException | IOException e) {
           logger.error(e.getMessage(),e);
        }
        return null;
    }

    @Override
    public String getJsApiTicketFromCache(){
        if(jsApiTicket == null){
            return getJsApiTicket().getTicket();
        }

        Long expiresInTime = jsApiTicket.getExpiresIn();
        Long accessTime = jsApiTicket.getAccessTime();
        //已失效
        if ((System.currentTimeMillis()/1000 - accessTime) >= expiresInTime){
            return getJsApiTicket().getTicket();
        }else {
            return jsApiTicket.getTicket();
        }
    }

    @Override
    public WechatJsSignature getSignature(String url){
        try {
            //10位随机串
            String noncestr = RandomStringUtils.randomAlphanumeric(10);
            String jsapiTicket = getJsApiTicketFromCache();
            Long timestamp = System.currentTimeMillis()/1000;

            String stringBuilder = "jsapi_ticket=" + jsapiTicket +
                    "&noncestr=" + noncestr +
                    "&timestamp=" + timestamp +
                    "&url=" + URLDecoder.decode(url, "UTF-8");

            String signature = DigestUtils.sha1Hex(stringBuilder);
            WechatJsSignature jsSignature = new WechatJsSignature();
            jsSignature.setAppId(wechatService.getConfFromCache().getAppId());
            jsSignature.setNonceStr(noncestr);
            jsSignature.setSignature(signature);
            jsSignature.setTimestamp(timestamp);

            return jsSignature;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }
}
