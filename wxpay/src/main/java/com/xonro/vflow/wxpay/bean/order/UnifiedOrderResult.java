package com.xonro.vflow.wxpay.bean.order;

import com.alibaba.fastjson.annotation.JSONField;
import com.xonro.vflow.wxpay.bean.WxPayResponse;

import java.io.Serializable;

/**
 * 统一下单结果
 * @author Alex
 * @date 2018/1/2
 */
public class UnifiedOrderResult extends WxPayResponse implements Serializable{
    /**
     * 设备号
     */
    @JSONField(name = "device_info")
    private String deviceInfo;
    /**
     * 交易类型
     */
    @JSONField(name = "trade_type")
    private String tradeType;
    /**
     * 预支付交易会话标识
     */
    @JSONField(name = "prepay_id")
    private String prepayId;
    /**
     * 二维码链接
     */
    @JSONField(name = "code_url")
    private String codeUrl;


    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

}
