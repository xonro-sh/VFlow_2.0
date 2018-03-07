package com.xonro.vflow.wxpay.service.impl;

import com.xonro.vflow.bases.helper.ConfManager;
import com.xonro.vflow.wxpay.bean.order.UnifiedOrderResult;
import com.xonro.vflow.wxpay.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author louie
 * @Description: 微信支付相关业务接口实现
 * @date 2018-3-6 15:16
 */
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ConfManager confManager;

    @Override
    public UnifiedOrderResult unifiedOrder(String body, String tradeNo, Integer totalFee, String openId) {

        return null;
    }
}
