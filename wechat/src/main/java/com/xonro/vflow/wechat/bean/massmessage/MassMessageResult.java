package com.xonro.vflow.wechat.bean.massmessage;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author Alex
 * @date 2018/1/12
 */
public class MassMessageResult {
    private String errCode;
    private String errMsg;
    private String msgId;
    private String msgDataId;
    private String type;
    /**
     * 消息发送后的状态，SEND_SUCCESS表示发送成功，SENDING表示发送中，SEND_FAIL表示发送失败，DELETE表示已删除
     */
    private String msgStatus;

    @JSONField(name = "errcode")
    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    @JSONField(name = "errmsg")
    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @JSONField(name = "msg_id")
    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @JSONField(name = "msg_data_id")
    public String getMsgDataId() {
        return msgDataId;
    }

    public void setMsgDataId(String msgDataId) {
        this.msgDataId = msgDataId;
    }

    @JSONField(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JSONField(name = "msg_status")
    public String getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(String msgStatus) {
        this.msgStatus = msgStatus;
    }

}
