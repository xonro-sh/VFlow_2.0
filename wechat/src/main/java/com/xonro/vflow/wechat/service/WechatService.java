package com.xonro.vflow.wechat.service;

import com.xonro.vflow.bases.bean.BaseResponse;
import com.xonro.vflow.bases.bean.WechatConf;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.wechat.bean.QrCode;
import com.xonro.vflow.wechat.bean.message.Message;

import java.io.IOException;

/**
 * 公众平台提供的相关服务
 * @author louie
 * @date 2018-1-12
 */
public interface WechatService {

    /**
     * 创建含有参数的二维码
     * @param expireSeconds 二维码有效时间，以秒为单位，最大不超过2592000（即30天）,为0或小于0时创建永久二维码。
     * @param sceneValue 生成的二维码所含的参数信息
     * @return 微信返回的二维码信息
     * @throws IOException 异常
     * @throws VFlowException 异常
     */
    public QrCode createQrCode(Long expireSeconds, Object sceneValue) throws IOException, VFlowException;

    /**
     * 获取公众平台生成的二维码图片
     * @param ticket 获取图片的凭证，通过创建二维码接口获取
     * @return 二维码图片的字节流
     */
    public byte[] getQrCodeImage(String ticket);

    /**
     * 更新微信服务号配置
     * @param wechatConf 服务号配置实体
     * @return 结果
     */
    BaseResponse updateServiceNoConf(WechatConf wechatConf);

    /**
     * 获取微信服务号配置
     * @return 结果
     */
    BaseResponse getServiceNoConf();

    /**
     * 获取缓存的公众号配置
     * @return token
     */
    WechatConf getConfFromCache();

    /**
     * 获取消息配置
     * @param type 消息类型（大类）
     * @return 结果
     */
    BaseResponse getMessageConf(String type);

    /**
     * 更新消息设置
     * @param message 信息对象
     * @return 结果
     */
    BaseResponse updateMessageConf(Message message);
}
