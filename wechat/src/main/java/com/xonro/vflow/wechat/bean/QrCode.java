package com.xonro.vflow.wechat.bean;

/**
 * 微信公众平台生成含有参数的二维码
 * @author louie
 * @date 2018-1-12
 */
public class QrCode {
    public QrCode(){}

    public QrCode(long expireSeconds, long createTime, String url, byte[] qrCode){
        this.createTime = createTime;
        this.expireSeconds = expireSeconds;
        this.url = url;
        this.qrCode = qrCode;
    }
    /**
     * 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）
     */
    private long expireSeconds;

    /**
     * 二维码创建时的时间戳，单位为秒
     */
    private long createTime;

    /**
     * 二维码图片解析后的地址
     */
    private String url;

    /**
     * 二维码图片字节码
     */
    private byte[] qrCode;

    public long getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(long expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getQrCode() {
        return qrCode;
    }

    public void setQrCode(byte[] qrCode) {
        this.qrCode = qrCode;
    }
}
