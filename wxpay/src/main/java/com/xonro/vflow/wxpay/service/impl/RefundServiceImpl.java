package com.xonro.vflow.wxpay.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.xonro.vflow.bases.bean.WxPayConf;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.bases.helper.ConfManager;
import com.xonro.vflow.wxpay.bean.refund.Refund;
import com.xonro.vflow.wxpay.bean.refund.RefundResult;
import com.xonro.vflow.wxpay.helper.ServiceRequestHelper;
import com.xonro.vflow.wxpay.service.RefundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author louie
 * @date created in 2018-3-9 15:45
 */
@Service
public class RefundServiceImpl extends ServiceRequestHelper implements RefundService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WXPayConfig wxPayConfig;
    @Autowired
    private ConfManager confManager;

    @Override
    public RefundResult refund(Refund refund) {
        WxPayConf wxPayConf = confManager.getWxPayConf();
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5,wxPayConf.isUseSandBox());
        try {
            Map<String,String> result = wxPay.refund(JSON.parseObject(JSON.toJSONString(refund), Map.class));
            if (validateRequestResult(result)){
                RefundResult refundResult = JSON.parseObject(JSON.toJSONString(refund),RefundResult.class);
                refundResult.setCoupons(parseRefundCoupon(result));
                return refundResult;
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
