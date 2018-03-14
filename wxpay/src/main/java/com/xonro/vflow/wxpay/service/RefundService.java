package com.xonro.vflow.wxpay.service;

import com.xonro.vflow.wxpay.bean.WxPayResponse;
import com.xonro.vflow.wxpay.bean.refund.QueryRefundResult;
import com.xonro.vflow.wxpay.bean.refund.Refund;
import com.xonro.vflow.wxpay.bean.refund.RefundResult;

/**
 * 退款业务相关服务接口
 * @author louie
 * @date created in 2018-3-9 14:46
 */
public interface RefundService{

    final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";
    final String KEY_ALGORITHM = "AES";

    /**
     * 申请微信退款
     * @param refund
     * @return
     */
    public RefundResult refund(Refund refund);

    /**
     * 根据微信订单号查询退款
     * @param tradeId
     * @return
     */
    public QueryRefundResult queryRefundByTradeId(String tradeId);

    /**
     * 根据商户订单号查询退款
     * @param outTradeId
     * @return
     */
    public QueryRefundResult queryRefundByOutTradeId(String outTradeId);

    /**
     * 根据微信退款单号查询退款
     * @param refundId
     * @return
     */
    public QueryRefundResult queryRefundByRefundId(String refundId);

    /**
     * 根据商户退款单号查询退款
     * @param outRefundId
     * @return
     */
    public QueryRefundResult queryRefundByOutRefundId(String outRefundId);

    /**
     * 退款结果通知接收并处理
     * @param notifyXml 微信通知数据
     * @return
     */
    public WxPayResponse accessRefundNotify(String notifyXml);

}
