package com.xonro.vflow.wechat.service;

import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.wechat.bean.WechatJsApiTicket;
import com.xonro.vflow.wechat.bean.WechatJsSignature;
import com.xonro.vflow.wechat.exception.WechatException;

import java.io.IOException;

/**
 * jsapi相关业务接口
 * @author Alex
 * @date 2018/1/9
 */
public interface JsApiService {
    /**
     * 获取微信公众平台jsapi_ticket临时票据
     * @return  jsapi_ticket
     */
    WechatJsApiTicket getJsApiTicket();

    /**
     * 从缓存中获取jsapi临时票据
     * @return 从缓存中获取jsapi临时票据
     */
    String getJsApiTicketFromCache();

    /**
     * 生成指定url地址的js signature
     * @param url 链接地址
     * @return js签名
     */
    WechatJsSignature getSignature(String url);
}
