package com.xonro.vflow.wxpay.bean.refund;

import com.alibaba.fastjson.annotation.JSONField;
import com.xonro.vflow.wxpay.bean.WxPayResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 退款单查询结果
 * @author louie
 * @date created in 2018-3-9 18:22
 */
public class QueryRefundResult extends WxPayResponse implements Serializable{
    /**
     * 订单总退款次数
     */
    @JSONField(name = "total_refund_count")
    private Integer totalRefundCount;

    /**
     * 微信订单号
     */
    @JSONField(name = "transaction_id")
    private String transactionId;

    /***
     * 商户订单号
     */
    @JSONField(name = "out_trade_no")
    private String outTradeNo;

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
     * 退款笔数
     */
    @JSONField(name = "refund_count")
    private Integer refundCount;

    /**
     * 退款明细
     */
    @JSONField(serialize = false)
    private List<RefundDetail> refundDetails;

    public Integer getTotalRefundCount() {
        return totalRefundCount;
    }

    public void setTotalRefundCount(Integer totalRefundCount) {
        this.totalRefundCount = totalRefundCount;
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

    public Integer getRefundCount() {
        return refundCount;
    }

    public void setRefundCount(Integer refundCount) {
        this.refundCount = refundCount;
    }

    public List<RefundDetail> getRefundDetails() {
        return refundDetails;
    }

    public void setRefundDetails(List<RefundDetail> refundDetails) {
        this.refundDetails = refundDetails;
    }
}
