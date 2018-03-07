package com.xonro.vflow.wxpay.bean.order;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author louie
 * @Description:统一下单
 * @date 2018-3-6 16:40
 */
@Entity
public class UnifiedOrder implements Serializable{
    /**
     * 设备号，自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
     */
    @JSONField(name = "device_info")
    private String deviceInfo = "WEB";

    /**
     * 随机字符串
     */
    @JSONField(name = "nonce_str")
    private String nonceStr;

    /**
     *商品描述
     */
    @JSONField(name = "body")
    private String body;

    /**
     * 商品详情
     */
    @JSONField(name = "detail")
    private String detail;

    /**
     * 附加数据
     */
    @JSONField(name = "attach")
    private String attach;

    /**
     * 商户订单号
     */
    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 标价币种,符合ISO 4217标准的三位字母代码，默认人民币：CNY
     */
    @JSONField(name = "fee_type")
    private String feeType = "CNY";

    /**
     * 标价金额
     */
    @JSONField(name = "total_fee")
    private Integer totalFee;

    /**
     * 终端IP
     */
    @JSONField(name = "spbill_create_ip")
    private String spBillCreateIp;

    /**
     * 交易起始时间
     */
    @JSONField(name = "time_start")
    private String timeStart;

    /**
     * 交易结束时间
     */
    @JSONField(name = "time_expire")
    private String timeExpire;

    /**
     * 订单优惠标记
     */
    @JSONField(name = "goods_tag")
    private String goodsTag;

    /**
     * 通知地址
     */
    @JSONField(name = "notify_url")
    private String notifyUrl;

    /**
     * 交易类型,取值如下：JSAPI，NATIVE，APP等
     */
    @JSONField(name = "trade_type")
    private String tradeType = "JSAPI";

    /**
     * 商品ID,tradeType=NATIVE时（即扫码支付），此参数必传。此参数为二维码中包含的商品ID，商户自行定义
     */
    @JSONField(name = "product_id")
    private String productId;

    /**
     * 指定支付方式 上传此参数no_credit--可限制用户不能使用信用卡支付
     */
    @JSONField(name = "limit_pay")
    private String limitPay;

    /**
     * 用户标识 ,tradeType=JSAPI时（即公众号支付），此参数必传
     */
    @JSONField(name = "openid")
    private String openid;

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpBillCreateIp() {
        return spBillCreateIp;
    }

    public void setSpBillCreateIp(String spBillCreateIp) {
        this.spBillCreateIp = spBillCreateIp;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
