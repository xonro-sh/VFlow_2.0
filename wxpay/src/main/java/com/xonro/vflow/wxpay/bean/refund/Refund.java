package com.xonro.vflow.wxpay.bean.refund;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 申请退款模型
 * @author louie
 * @date created in 2018-03-09 11:30
 */
@Component
public class Refund implements Serializable{
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
     * 退款金额
     */
    @JSONField(name = "refund_fee")
    private Integer refundFee;
    /**
     * 货币种类
     */
    @JSONField(name = "refund_fee_type")
    private String refundFeeType="CNY";
    /**
     * 退款原因
     */
    @JSONField(name = "refund_desc")
    private String refundDesc;
    /**
     * 退款资金来源
     * 仅针对老资金流商户使用
     * REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）
     * REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
     */
    @JSONField(name = "refund_account")
    private String refundAccount;

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

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }

    public String getRefundFeeType() {
        return refundFeeType;
    }

    public void setRefundFeeType(String refundFeeType) {
        this.refundFeeType = refundFeeType;
    }

    public String getRefundDesc() {
        return refundDesc;
    }

    public void setRefundDesc(String refundDesc) {
        this.refundDesc = refundDesc;
    }

    public String getRefundAccount() {
        return refundAccount;
    }

    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount;
    }
}
