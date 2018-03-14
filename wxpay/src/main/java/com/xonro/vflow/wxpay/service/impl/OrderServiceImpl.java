package com.xonro.vflow.wxpay.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.xonro.vflow.bases.bean.WxPayConf;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.bases.helper.ConfManager;
import com.xonro.vflow.wxpay.bean.order.CloseOrderResult;
import com.xonro.vflow.wxpay.bean.order.QueryOrderResult;
import com.xonro.vflow.wxpay.bean.order.UnifiedOrder;
import com.xonro.vflow.wxpay.bean.order.UnifiedOrderResult;
import com.xonro.vflow.wxpay.helper.ServiceRequestHelper;
import com.xonro.vflow.wxpay.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付相关业务接口实现
 * @author louie
 * @date created in 2018-3-6 15:16
 */
@Service
public class OrderServiceImpl extends ServiceRequestHelper implements OrderService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConfManager confManager;

    @Autowired
    private WXPayConfig wxPayConfig;

    @Override
    public UnifiedOrderResult unifiedOrder(String body, String tradeNo, Integer totalFee, String openId) {
        WxPayConf wxPayConf = confManager.getWxPayConf();
        WXPay wxPay = new WXPay(wxPayConfig,WXPayConstants.SignType.MD5,wxPayConf.isUseSandBox());
        try {
            String clientIp = InetAddress.getLocalHost().getHostAddress();
            UnifiedOrder unifiedOrder = new UnifiedOrder(body,tradeNo,totalFee,openId,clientIp,wxPayConf.getNotifyUrl());

            //将请求模型转为请求参数集合，并执行微信支付接口访问
            Map params = JSON.parseObject(JSON.toJSONString(unifiedOrder),Map.class);
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

    @Override
    public QueryOrderResult queryOrderByTradeId(String tradeId) {
        Map<String,String> params = new HashMap<String, String>(4){{
            put("transaction_id",tradeId);
        }};
        return orderQuery(params);
    }

    @Override
    public QueryOrderResult queryOrderByOutTradeId(String outTradeId) {
        Map<String,String> params = new HashMap<String, String>(4){{
            put("out_trade_no",outTradeId);
        }};
        return orderQuery(params);
    }

    @Override
    public CloseOrderResult closeOrder(String outTradeId) {
        WxPayConf wxPayConf = confManager.getWxPayConf();
        WXPay wxPay = new WXPay(wxPayConfig,WXPayConstants.SignType.MD5,wxPayConf.isUseSandBox());

        Map<String,String> params = new HashMap<String, String>(4){{
            put("out_trade_no",outTradeId);
        }};

        try {
            Map<String,String> result = wxPay.closeOrder(params);
            if (validateRequestResult(result)){
                return JSON.parseObject(JSON.toJSONString(result),CloseOrderResult.class);
            }
        } catch (VFlowException e){
          logger.error(e.getMessage(),e);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 执行订单查询
     * @param params
     * @return
     */
    private QueryOrderResult orderQuery(Map<String,String> params){
        WxPayConf wxPayConf = confManager.getWxPayConf();
        WXPay wxPay = new WXPay(wxPayConfig,WXPayConstants.SignType.MD5,wxPayConf.isUseSandBox());

        try {
            Map<String,String> result = wxPay.orderQuery(params);
            if (validateRequestResult(result)){
                QueryOrderResult queryOrderResult = JSON.parseObject(JSON.toJSONString(result),QueryOrderResult.class);
                queryOrderResult.setCoupons(parseOrderCoupon(result));
                return queryOrderResult;
            }
        } catch (VFlowException e){
            logger.error(e.getMessage(),e);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

}
