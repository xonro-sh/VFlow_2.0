package com.xonro.vflow.wxpay.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.xonro.vflow.bases.bean.WxPayConf;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.bases.helper.ConfManager;
import com.xonro.vflow.wxpay.bean.order.UnifiedOrder;
import com.xonro.vflow.wxpay.bean.order.UnifiedOrderResult;
import com.xonro.vflow.wxpay.service.OrderService;
import com.xonro.vflow.wxpay.web.RequestHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;
import java.util.Map;

/**
 * 微信支付相关业务接口实现
 * @author louie
 * @date created in 2018-3-6 15:16
 */
public class OrderServiceImpl extends RequestHelper implements OrderService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConfManager confManager;

    @Autowired
    private WXPayConfig wxPayConfig;

    @Override
    public UnifiedOrderResult unifiedOrder(String body, String tradeNo, Integer totalFee, String openId) {
        WxPayConf wxPayConf = confManager.getWxPayConf();
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5,wxPayConf.isUseSandBox());
        try {
            String clientIp = InetAddress.getLocalHost().getHostAddress();
            UnifiedOrder unifiedOrder = new UnifiedOrder(body,tradeNo,totalFee,openId,clientIp,wxPayConf.getNotifyUrl());

            //将请求模型转为请求参数集合，并执行微信支付接口访问
            Map params = JSON.parseObject(JSON.toJSONString(unifiedOrder));
            Map<String,String> result = wxPay.unifiedOrder(params);
            if (validateRequestResult(result)){
                return JSON.parseObject(JSON.toJSONString(result),UnifiedOrderResult.class);
            }
        } catch (VFlowException e){
            logger.error(e.getMessage(),e);
        }
        catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }
}
