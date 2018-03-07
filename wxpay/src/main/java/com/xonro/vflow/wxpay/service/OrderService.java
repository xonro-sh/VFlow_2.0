package com.xonro.vflow.wxpay.service;

import com.xonro.vflow.wxpay.bean.order.UnifiedOrder;
import com.xonro.vflow.wxpay.bean.order.UnifiedOrderResult;

import javax.validation.constraints.NotNull;

/**
 * @author louie
 * @Description: 微信支付相关业务服务接口
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
    public UnifiedOrderResult unifiedOrder(String body, String tradeNo, Integer totalFee, String openId);
}
