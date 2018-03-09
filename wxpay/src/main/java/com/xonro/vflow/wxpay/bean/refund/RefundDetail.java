package com.xonro.vflow.wxpay.bean.refund;

import com.xonro.vflow.wxpay.bean.Coupon;

import java.io.Serializable;
import java.util.List;

/**
 * 退款明细
 * @author louie
 * @date created in 2018-3-9 18:30
 */
public class RefundDetail implements Serializable {

    /**
     * 商户退款单号
     */
    private String outRefundNo;

    /**
     * 微信退款单号
     */
    private String refundId;

    /**
     * 退款渠道
     */
    private String refundChannel;

    /**
     * 申请退款金额
     */
    private Integer refundFee;

    /**
     * 退款金额
     */
    private Integer settlementRefundFee;

    /**
     * 总代金券退款金额
     */
    private Integer couponRefundFee;

    /**
     * 退款代金券使用数量
     */
    private Integer couponRefundCount;

    /**
     * 退款状态
     */
    private String refundStatus;

    /**
     * 退款资金来源
     */
    private String refundAccount;

    /**
     * 退款入账账户
     */
    private String refundRecvAccout;

    /**
     * 退款成功时间
     */
    private String refundSuccessTime;

    /**
     * 代金券明细
     */
    private List<Coupon> coupons;

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

    public String getRefundChannel() {
        return refundChannel;
    }

    public void setRefundChannel(String refundChannel) {
        this.refundChannel = refundChannel;
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

    public Integer getCouponRefundFee() {
        return couponRefundFee;
    }

    public void setCouponRefundFee(Integer couponRefundFee) {
        this.couponRefundFee = couponRefundFee;
    }

    public Integer getCouponRefundCount() {
        return couponRefundCount;
    }

    public void setCouponRefundCount(Integer couponRefundCount) {
        this.couponRefundCount = couponRefundCount;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getRefundAccount() {
        return refundAccount;
    }

    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount;
    }

    public String getRefundRecvAccout() {
        return refundRecvAccout;
    }

    public void setRefundRecvAccout(String refundRecvAccout) {
        this.refundRecvAccout = refundRecvAccout;
    }

    public String getRefundSuccessTime() {
        return refundSuccessTime;
    }

    public void setRefundSuccessTime(String refundSuccessTime) {
        this.refundSuccessTime = refundSuccessTime;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
}
