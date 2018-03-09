package com.xonro.vflow.wxpay.bean.order;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 关闭订单结果模型
 * @author Alex
 * @date 2018/1/3
 */
public class CloseOrderResult implements Serializable{
    /**
     * 返回状态码  SUCCESS/FAIL
     */
    @JSONField(name = "return_code")
    private String returnCode;
    /**
     * 返回信息
     */
    @JSONField(name = "return_msg")
    private String returnMsg;
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
    /**
     * 业务结果
     */
    @JSONField(name = "result_code")
    private String resultCode;
    /**
     * 业务结果描述
     */
    @JSONField(name = "result_msg")
    private String resultMsg;
    /**
     * 错误代码
     */
    @JSONField(name = "err_code")
    private String errCode;
    /**
     * 错误代码描述
     */
    @JSONField(name = "err_code_des")
    private String errCodeDes;

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

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
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
}
