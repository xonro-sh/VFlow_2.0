package com.xonro.vflow.wechat.bean.message.ordinary;

import com.alibaba.fastjson.annotation.JSONField;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 用户通过微信向公众号发送普通的消息
 * @author louie
 * @date 2018-2-9
 */

@XStreamAlias("xml")
public class OrdinaryMsg {

    /**
     * 开发者微信号
     */
    @JSONField(name = "ToUserName")
    @XStreamAlias("ToUserName")
    private String toUserName;

    /**
     * 发送方帐号（一个OpenID）
     */
    @JSONField(name = "FromUserName")
    @XStreamAlias("FromUserName")
    private String fromUserName;

    /**
     * 消息创建时间 （整型）
     */
    @JSONField(name = "CreateTime")
    @XStreamAlias("CreateTime")
    private String createTime;

    /**
     * 消息类型
     */
    @JSONField(name = "MsgType")
    @XStreamAlias("MsgType")
    private String msgType;

    /**
     * 消息id，64位整型
     */
    @JSONField(name = "MsgId")
    @XStreamAlias("MsgId")
    private String msgId;

    /**
     * 	文本消息内容
     */
    @JSONField(name = "Content")
    @XStreamAlias("Content")
    private String content;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
