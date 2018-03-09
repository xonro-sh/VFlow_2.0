package com.xonro.vflow.wxpay.bean.order;

import com.alibaba.fastjson.annotation.JSONField;
import com.xonro.vflow.wxpay.bean.Coupon;

import java.io.Serializable;
import java.util.List;

/**
 * 订单查询结果
 * @author louie
 *
 * @date created in 2018-3-8 16:53
 */
public class QueryOrderResult implements Serializable {
    /**
     * 返回状态码
     */
    @JSONField(name = "return_code")
    private String returnCode;

    /**
     * 返回信息
     */
    @JSONField(name = "return_msg ")
    private String returnMsg;

    //以下字段在return_code为SUCCESS的时候有返回
    /**
     * 公众账号id
     */
    @JSONField(name = "appid")
    private String appId;

    /**
     * 商户号
     */
    @JSONField(name = "mch_id ")
    private String mchId;

    /**
     * 随机串
     */
    @JSONField(name = "nonce_str ")
    private String nonceStr;

    /**
     * 签名
     */
    @JSONField(name = "sign")
    private String sign;

    /**
     * 业务结果
     */
    @JSONField(name = "result_code ")
    private String resultCode;

    /**
     * 错误代码
     */
    @JSONField(name = "err_code ")
    private String errCode;

    /**
     * 错误代码描述
     */
    @JSONField(name = "err_code_des")
    private String errCodeDes;

    //以下字段在return_code 、result_code、trade_state都为SUCCESS时有返回 ，如trade_state不为 SUCCESS，则只返回out_trade_no（必传）和attach（选传）

    /**
     * 设备号
     */
    @JSONField(name = "device_info")
    private String deviceInfo;

    /**
     * 用户标识
     */
    @JSONField(name = "openid")
    private String openId;

    /**
     * 是否关注公众账号 Y-关注，N-未关注
     */
    @JSONField(name = "is_subscribe")
    private String isSubscribe;

    /**
     * 交易类型
     */
    @JSONField(name = "trade_type")
    private String tradeType;

    /**
     * 交易状态
     * SUCCESS—支付成功
     * REFUND—转入退款
     * NOTPAY—未支付
     * CLOSED—已关闭
     * REVOKED—已撤销（刷卡支付）
     * USERPAYING--用户支付中
     * PAYERROR--支付失败(其他原因，如银行返回失败)
     s*/
    @JSONField(name = "trade_state")
    private String tradeState;

    /**
     * 付款银行
     */
    @JSONField(name = "bank_type")
    private String bankType;

    /**
     * 订单总金额，单位为分
     */
    @JSONField(name = "total_fee")
    private Integer totalFee;

    /**
     * 应结订单金额   应结订单金额=订单金额-免充值优惠券金额
     */
    @JSONField(name = "settlement_total_fee")
    private Integer settlementTotalFee;

    /**
     * 标价币种
     */
    @JSONField(name = "fee_type")
    private String feeType = "CNY";

    /**
     * 现金支付金额
     */
    @JSONField(name = "cash_fee")
    private Integer cashFee;

    /**
     * 现金支付币种
     */
    @JSONField(name = "cash_fee_type ")
    private String cashFeeType = "CNY";

    /**
     * 代金券金额
     */
    @JSONField(name = "coupon_fee")
    private Integer couponFee;

    /**
     * 代金券使用数量
     */
    @JSONField(name = "coupon_count")
    private Integer couponCount;

    /**
     * 微信支付订单号
     */
    @JSONField(name = "transaction_id")
    private String transactionId;

    /**
     * 商户订单号
     */
    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 附加数据
     */
    @JSONField(name = "attach")
    private String attach;

    /**
     * 支付完成时间
     */
    @JSONField(name = "time_end")
    private String timeEnd;

    /**
     * 交易状态描述
     */
    @JSONField(name = "trade_state_desc")
    private String tradeStateDesc;

    /**
     * 代金券
     */
    private List<Coupon> coupons;

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

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getSettlementTotalFee() {
        return settlementTotalFee;
    }

    public void setSettlementTotalFee(Integer settlementTotalFee) {
        this.settlementTotalFee = settlementTotalFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Integer getCashFee() {
        return cashFee;
    }

    public void setCashFee(Integer cashFee) {
        this.cashFee = cashFee;
    }

    public String getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType;
    }

    public Integer getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(Integer couponFee) {
        this.couponFee = couponFee;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getTradeStateDesc() {
        return tradeStateDesc;
    }

    public void setTradeStateDesc(String tradeStateDesc) {
        this.tradeStateDesc = tradeStateDesc;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
}
