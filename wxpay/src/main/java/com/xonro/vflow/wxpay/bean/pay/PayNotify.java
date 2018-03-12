package com.xonro.vflow.wxpay.bean.pay;

import com.alibaba.fastjson.annotation.JSONField;
import com.xonro.vflow.wxpay.bean.Coupon;
import com.xonro.vflow.wxpay.bean.WxPayResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 支付结果通知
 * @author louie
 * @date created in 2018-3-12 10:46
 */
public class PayNotify extends WxPayResponse implements Serializable{

    /**
     * 微信支付分配的终端设备号
     */
    @JSONField(name = "device_info")
    private String deviceInfo;

    /**
     * 用户标识
     */
    @JSONField(name = "openid")
    private String openId;

    /**
     * 是否关注公众账号
     */
    @JSONField(name = "is_subscribe")
    private String isSubscribe;

    /**
     * 交易类型
     */
    @JSONField(name = "trade_type")
    private String tradeType;

    /**
     * 付款银行
     */
    @JSONField(name = "bank_type")
    private String bankType;

    /**
     * 订单金额
     */
    @JSONField(name = "total_fee")
    private Integer totalFee;

    /**
     * 应结订单金额
     */
    @JSONField(name = "settlement_total_fee")
    private Integer settlementTotalFee;

    /**
     * 货币种类
     */
    @JSONField(name = "fee_type")
    private String feeType = "CNY";

    /**
     * 现金支付金额
     */
    @JSONField(name = "cash_fee")
    private Integer cashFee;

    /**
     * 现金支付货币类型
     */
    @JSONField(name = "cash_fee_type")
    private String cashFeeType = "CNY";

    /**
     * 总代金券金额
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
     * 商家数据包
     */
    @JSONField(name = "attach")
    private String attach;

    /**
     * 支付完成时间
     */
    @JSONField(name = "time_end")
    private String timeEnd;

    /**
     * 代金券
     */
    @JSONField(serialize = false)
    private List<Coupon> coupons;

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

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        return "PayNotify{" +
                "deviceInfo='" + deviceInfo + '\'' +
                ", openId='" + openId + '\'' +
                ", isSubscribe='" + isSubscribe + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", bankType='" + bankType + '\'' +
                ", totalFee=" + totalFee +
                ", settlementTotalFee=" + settlementTotalFee +
                ", feeType='" + feeType + '\'' +
                ", cashFee=" + cashFee +
                ", cashFeeType='" + cashFeeType + '\'' +
                ", couponFee=" + couponFee +
                ", couponCount=" + couponCount +
                ", transactionId='" + transactionId + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", attach='" + attach + '\'' +
                ", timeEnd='" + timeEnd + '\'' +
                ", coupons=" + coupons +
                '}';
    }
}
