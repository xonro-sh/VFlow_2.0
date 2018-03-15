package com.xonro.vflow.wxpay.service;

import com.xonro.vflow.wxpay.bean.order.CloseOrderResult;
import com.xonro.vflow.wxpay.bean.order.QueryOrderResult;
import com.xonro.vflow.wxpay.bean.order.UnifiedOrderResult;

/**
 * 微信支付订单相关业务服务接口
 * @author louie
 * @date 2018-3-6 15:16
 */
public interface OrderService {

    /**
     * 统一下单
     * @param body 商品描述
     * @param tradeNo 系统内部订单号
     * @param totalFee 标价金额
     * @param openId 用户标识
     * @return 预支付信息
     */
    UnifiedOrderResult unifiedOrder(String body, String tradeNo, Integer totalFee, String openId);

    /**
     * 订单查询：根据微信订单号查询订单详情
     * @param tradeId 微信订单号
     * @return
     */
    QueryOrderResult queryOrderByTradeId(String tradeId);

    /**
     * 订单查询：根据商户订单号查询订单详情
     * @param outTradeId
     * @return
     */
    QueryOrderResult queryOrderByOutTradeId(String outTradeId);

    /**
     * 关闭微信订单
     * @param outTradeId 商户订单号
     * @return
     */
    CloseOrderResult closeOrder(String outTradeId);

}
