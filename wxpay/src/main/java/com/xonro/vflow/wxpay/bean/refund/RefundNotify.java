package com.xonro.vflow.wxpay.bean.refund;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 退款结果通知
 * @author louie
 * @date created in 2018-3-13 14:31
 */
public class RefundNotify implements Serializable{
    /**
     * 微信订单号
     */
    @JSONField(name = "transaction_id")
    private String transactionId;

    /**
     * 商户订单号
     */
    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 微信退款单号
     */
    @JSONField(name = "refund_id")
    private String refundId;

    /**
     * 商户退款单号
     */
    @JSONField(name = "out_refund_no")
    private String outRefundNo;

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
     * 申请退款金额
     */
    @JSONField(name = "refund_fee")
    private Integer refundFee;

    /**
     * 退款金额
     */
    @JSONField(name = "settlement_refund_fee")
    private Integer settlementRefundFee;

    /**
     * 退款状态
     */
    @JSONField(name = "refund_status")
    private String refundStatus;

    /**
     * 退款成功时间
     */
    @JSONField(name = "success_time")
    private String successTime;

    /**
     * 退款入账账户
     */
    @JSONField(name = "refund_recv_accout")
    private String refundRecvAccout;

    /**
     * 退款资金来源
     */
    @JSONField(name = "refund_account")
    private String refundAccount;

    /**
     * 退款发起来源
     */
    @JSONField(name = "refund_request_source")
    private String refundRequestSource;

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

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
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

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(String successTime) {
        this.successTime = successTime;
    }

    public String getRefundRecvAccout() {
        return refundRecvAccout;
    }

    public void setRefundRecvAccout(String refundRecvAccout) {
        this.refundRecvAccout = refundRecvAccout;
    }

    public String getRefundAccount() {
        return refundAccount;
    }

    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount;
    }

    public String getRefundRequestSource() {
        return refundRequestSource;
    }

    public void setRefundRequestSource(String refundRequestSource) {
        this.refundRequestSource = refundRequestSource;
    }
}
