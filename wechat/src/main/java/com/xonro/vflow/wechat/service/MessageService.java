package com.xonro.vflow.wechat.service;

import com.xonro.vflow.wechat.bean.message.ordinary.OrdinaryMsg;

/**
 * 消息相关业务服务接口
 * @author louie modified by Alex
 * @date 2018-1-3
 */
public interface MessageService {
    /**
     * 解析微信平台推送的消息
     * @param xmlMessage 微信平台post的消息xml
     * @return 是否成功 返回""或者SUCCESS都为成功
     */
    public String parseMessage(String xmlMessage);

    /**
     * 被动回复用户消息
     * @param ordinaryMsg 回复用户消息对象
     * @return 回复用户的xml格式消息
     */
    public String replyMessage(OrdinaryMsg ordinaryMsg);
}
