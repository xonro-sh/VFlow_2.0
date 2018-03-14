package com.xonro.vflow.wxpay.bean.bill;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Alex
 * @date 2018/1/31
 */
@Entity
@Table(name="b_xr_wxpay_bill_all")
public class Bill implements Serializable{
    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    /**
     * 账单日期
     */
    private String billDate;
    /**
     * 交易时间
     */
    private String tradeTime;
    /**
     * 公众账号ID
     */
    private String appId;
    /**
     * 商户号
     */
    private String mchId;
    /**
     * 子商户号
     */
    private String subMchId;
    /**
     * 设备号
     */
    private String deviceId;
    /**
     * 微信订单号
     */
    private String transactionId;
    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 用户标识
     */
    private String openId;
    /**
     * 交易类型
     */
    private String tradeType;
    /**
     * 交易状态
     */
    private String tradeStatus;
    /**
     * 付款银行
     */
    private String bank;
    /**
     * 货币种类
     */
    private String feeType;
    /**
     * 总金额
     */
    private String totalFee;
    /**
     * 代金券或立减优惠金额
     */
    private String redpacketFee;
    /**
     * 微信退款单号
     */
    private String refundId;
    /**
     * 商户退款单号
     */
    private String outRefundNo;
    /**
     * 退款金额
     */
    private String refundFee;
    /**
     * 代金券或立减优惠退款金额
     */
    private String redpacketRefund;
    /**
     * 退款类型
     */
    private String refundType;
    /**
     * 退款状态
     */
    private String refundStatus;
    /**
     * 商品名称
     */
    private String body;
    /**
     * 商户数据包
     */
    private String dataPacket;
    /**
     * 手续费
     */
    private String fee;
    /**
     * 费率
     */
    private String rate;
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name = "bill_date")
    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    @Column(name = "trade_time")
    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }
    @Column(name = "app_id")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
    @Column(name = "mch_id")
    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }
    @Column(name = "sub_mch_id")
    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }
    @Column(name = "device_id")
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    @Column(name = "transaction_id")
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    @Column(name = "out_trade_no")
    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
    @Column(name = "open_id")
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
    @Column(name = "trade_type")
    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
    @Column(name = "trade_status")
    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }
    @Column(name = "bank")
    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
    @Column(name = "fee_type")
    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }
    @Column(name = "total_fee")
    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }
    @Column(name = "redpacket_fee")
    public String getRedpacketFee() {
        return redpacketFee;
    }

    public void setRedpacketFee(String redpacketFee) {
        this.redpacketFee = redpacketFee;
    }
    @Column(name = "refund_id")
    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }
    @Column(name = "out_refund_no")
    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }
    @Column(name = "refund_fee")
    public String getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(String refundFee) {
        this.refundFee = refundFee;
    }
    @Column(name = "redpacket_refund")
    public String getRedpacketRefund() {
        return redpacketRefund;
    }

    public void setRedpacketRefund(String redpacketRefund) {
        this.redpacketRefund = redpacketRefund;
    }
    @Column(name = "refund_type")
    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }
    @Column(name = "refund_status")
    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }
    @Column(name = "body")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    @Column(name = "data_packet")
    public String getDataPacket() {
        return dataPacket;
    }

    public void setDataPacket(String dataPacket) {
        this.dataPacket = dataPacket;
    }
    @Column(name = "fee")
    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
    @Column(name = "rate")
    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
