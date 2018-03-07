package com.xonro.vflow.wechat.bean;

import java.io.Serializable;

/**
 * 微信公众平台js签名
 * @author Alex
 * @date 2018/1/9
 */
public class WechatJsSignature implements Serializable {
    private String appId;
    private Long timestamp;
    private String  nonceStr;
    private String signature;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
