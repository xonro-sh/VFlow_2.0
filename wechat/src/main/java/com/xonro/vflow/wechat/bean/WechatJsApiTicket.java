package com.xonro.vflow.wechat.bean;

/**
 * 微信公众平台jsapi_ticket
 * @author Alex
 * @date 2018/1/9
 */
public class WechatJsApiTicket {
    /**
     * 临时票据
     */
    private String ticket;
    /**
     * 有效时长
     */
    private Long expiresIn;
    /**
     * 获取日期
     */
    private Long accessTime;


    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }



    public Long getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Long accessTime) {
        this.accessTime = accessTime;
    }
}
