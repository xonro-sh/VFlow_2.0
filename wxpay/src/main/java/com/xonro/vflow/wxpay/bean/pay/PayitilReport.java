package com.xonro.vflow.wxpay.bean.pay;

import com.alibaba.fastjson.annotation.JSONField;
import com.xonro.vflow.wxpay.bean.WxPayResponse;

import java.io.Serializable;

/**
 * 微信支付数据上报
 * @author louie
 * @date created in 2018-3-12 17:59
 */
public class PayitilReport extends WxPayResponse implements Serializable {
    /**
     * 设备号:微信支付分配的终端设备号
     */
    @JSONField(name = "device_info")
    private String deviceInfo;

    /**
     * 接口URL
     */
    @JSONField(name = "interface_url")
    private String interfaceUrl;

    /**
     * 接口耗时
     */
    @JSONField(name = "execute_time")
    private Integer executeTime;

    /**
     * 商户订单号
     */
    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 访问接口IP:发起接口调用时的机器IP
     */
    @JSONField(name = "user_ip")
    private String userIp;

    /**
     * 商户上报时间,格式为yyyyMMddHHmmss
     */
    @JSONField(name = "time")
    private String time;

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl;
    }

    public Integer getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Integer executeTime) {
        this.executeTime = executeTime;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
