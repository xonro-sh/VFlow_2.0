package com.xonro.vflow.wxpay.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 微信支付接口响应基本信息
 * @author louie
 * @date created in 2018-3-9 18:04
 */
public class WxPayResponse implements Serializable{
    /**
     * 返回状态码
     */
    @JSONField(name = "return_code")
    private String returnCode;

    /**
     * 返回信息
     */
    @JSONField(name = "return_msg")
    private String returnMsg;

//    以下字段在return_code为SUCCESS的时候有返回
    /**
     * 业务结果
     */
    @JSONField(name = "result_code")
    private String resultCode;

    /**
     * 错误码
     */
    @JSONField(name = "err_code")
    private String errCode;

    /**
     * 错误描述
     */
    @JSONField(name = "err_code_des")
    private String errCodeDes;

    /**
     * 公众账号ID
     */
    @JSONField(name = "appid")
    private String appId;

    /**
     * 商户号
     */
    @JSONField(name = "mch_id")
    private String mchId;

    /**
     * 随机字符串
     */
    @JSONField(name = "nonce_str")
    private String nonceStr;

    /**
     * 签名
     */
    @JSONField(name = "sign")
    private String sign;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
