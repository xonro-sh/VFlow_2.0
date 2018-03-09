package com.xonro.vflow.wxpay.bean.refund;

import com.alibaba.fastjson.annotation.JSONField;
import com.xonro.vflow.wxpay.bean.Coupon;
import com.xonro.vflow.wxpay.bean.WxPayResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 微信退款结果
 * @author louie
 * @date created in 2018-3-9 11:31
 */
public class RefundResult extends WxPayResponse implements Serializable{
    /**
     * 微信订单号
     */
    @JSONField(name = "transaction_id")
    private String  transactionId;
    /**
     * 商户订单号
     */
    @JSONField(name = "out_trade_no")
    private String  outTradeNo;
    /**
     * 商户退款单号
     */
    @JSONField(name = "out_refund_no")
    private String  outRefundNo;
    /**
     * 微信退款单号
     */
    @JSONField(name = "refund_id")
    private String  refundId;
    /**
     * 退款金额
     */
    @JSONField(name = "refund_fee")
    private Integer  refundFee;
    /**
     * 应结退款金额
     */
    @JSONField(name = "settlement_refund_fee")
    private Integer  settlementRefundFee;
    /**
     * 标价金额
     */
    @JSONField(name = "total_fee")
    private Integer  totalFee;
    /**
     * 应结订单金额
     */
    @JSONField(name = "settlement_total_fee")
    private Integer  settlementTotalFee ;
    /**
     * 标价币种
     */
    @JSONField(name = "fee_type")
    private String  feeType = "CNY";
    /**
     * 现金支付金额
     */
    @JSONField(name = "cash_fee")
    private Integer  cashFee;
    /**
     * 现金支付币种
     */
    @JSONField(name = "cash_fee_type")
    private String  cashFeeType = "CNY";
    /**
     * 现金退款金额
     */
    @JSONField(name = "cash_refund_fee")
    private Integer  cashRefundFee;

    /**
     * 代金券退款总金额
     */
    @JSONField(name = "coupon_refund_fee")
    private Integer  couponRefundFee;

    /**
     * 退款代金券使用数量
     */
    @JSONField(name = "coupon_refund_count")
    private Integer  coupon_refund_count;

    /**
     * 退款代金券
     */
    @JSONField(serialize = false)
    private List<Coupon> coupons;

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

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public Integer getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }

    public Integer getSettlementRefundFee() {
        return settlementRefundFee;
    }

    public void setSettlementRefundFee(Integer settlementRefundFee) {
        this.settlementRefundFee = settlementRefundFee;
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

    public Integer getCashRefundFee() {
        return cashRefundFee;
    }

    public void setCashRefundFee(Integer cashRefundFee) {
        this.cashRefundFee = cashRefundFee;
    }

    public Integer getCouponRefundFee() {
        return couponRefundFee;
    }

    public void setCouponRefundFee(Integer couponRefundFee) {
        this.couponRefundFee = couponRefundFee;
    }

    public Integer getCoupon_refund_count() {
        return coupon_refund_count;
    }

    public void setCoupon_refund_count(Integer coupon_refund_count) {
        this.coupon_refund_count = coupon_refund_count;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
}
