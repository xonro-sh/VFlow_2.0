package com.xonro.vflow.bases.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author louie
 * @Description: 微信支付配置类
 * @date 2018-3-2 15:26
 */
@Entity
@Table(name = "b_xr_wxpay_conf")
public class WxPayConf implements Serializable{
    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    /**
     *微信支付商户号
     */
    private String mchId;

    /**
     * 微信商户支付密码
     */
    private String apiKey;

    /**
     * 微信支付结果通知地址
     */
    private String notifyUrl;

    /**
     * 退款结果通知地址：开通该功能需要在商户平台-交易中心-退款配置中配置notify_url
     */
    private String refundNotifyUrl;

    /**
     * 微信支付商户名称
     */
    private String businessName;

    /**
     * 是否启用沙箱
     */
    private boolean useSandBox;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public boolean isUseSandBox() {
        return useSandBox;
    }

    public void setUseSandBox(boolean useSandBox) {
        this.useSandBox = useSandBox;
    }

    public String getRefundNotifyUrl() {
        return refundNotifyUrl;
    }

    public void setRefundNotifyUrl(String refundNotifyUrl) {
        this.refundNotifyUrl = refundNotifyUrl;
    }

    @Override
    public String toString() {
        return "WxPayConf{" +
                "id='" + id + '\'' +
                ", mchId='" + mchId + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", businessName='" + businessName + '\'' +
                '}';
    }
}
