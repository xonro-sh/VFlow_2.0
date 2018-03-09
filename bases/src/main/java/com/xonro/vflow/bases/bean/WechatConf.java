package com.xonro.vflow.bases.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author louie
 * @Description: 微信公众号配置
 * @date 2018-3-2 11:46
 */
@Entity
@Table(name = "b_xr_wechat_conf")
public class WechatConf implements Serializable{
    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    /**
     * 公众号开发者标识
     */
    private String appId;

    /**
     * 公众号开发者密码
     */
    private String appSecret;

    /**
     * 公众号开发者令牌
     */
    private String token;

    /**
     * 微信公众号回调地址
     */
    private String callBackUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCallBackUrl() {
        return callBackUrl;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }

    @Override
    public String toString() {
        return "WechatConf{" +
                "id='" + id + '\'' +
                ", appId='" + appId + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", token='" + token + '\'' +
                ", callBackUrl='" + callBackUrl + '\'' +
                '}';
    }
}
