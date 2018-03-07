package com.xonro.vflow.wechat.bean;


/**
 * 微信身份凭证模型
 * @author Alex
 * @date 2018/1/8
 */
public class WechatAccessToken {
    /**
     * 获取到的凭证
     */
    private String accessToken;
    /**
     * 凭证有效时间，单位：秒
     */
    private Long expiresIn;
    /**
     * 获取时间，时间戳，单位秒
     */
    private Long accessTimestamp;


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Long getAccessTimestamp() {
        return accessTimestamp;
    }

    public void setAccessTimestamp(Long accessTimestamp) {
        this.accessTimestamp = accessTimestamp;
    }
}
