package com.xonro.vflow.wxpay.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.xonro.vflow.bases.bean.WxPayConf;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.bases.helper.ConfManager;
import com.xonro.vflow.wxpay.bean.BaseResponse;
import com.xonro.vflow.wxpay.bean.WxPayResponse;
import com.xonro.vflow.wxpay.bean.pay.PayNotify;
import com.xonro.vflow.wxpay.bean.pay.PayitilReport;
import com.xonro.vflow.wxpay.enums.WxPayEnum;
import com.xonro.vflow.wxpay.helper.ServiceRequestHelper;
import com.xonro.vflow.wxpay.service.PayService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author louie
 * @date created in 2018-3-12 16:57
 */
@Service
public class PayServiceImpl extends ServiceRequestHelper implements PayService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WXPayConfig wxPayConfig;

    @Autowired
    private ConfManager confManager;

    @Override
    public String accessPayNotify(String notifyData) {
        BaseResponse response = new BaseResponse(WxPayEnum.RETURN_CODE_OK.getValue());
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5);
        try {
            Map<String,String> notifyParams = WXPayUtil.xmlToMap(notifyData);
            if (wxPay.isPayResultNotifySignatureValid(notifyParams)){
                PayNotify payNotify = JSON.parseObject(JSON.toJSONString(notifyParams),PayNotify.class);
                String returnCode = payNotify.getReturnCode();

                //判断接口通信是否正确
                if (StringUtils.isNotEmpty(returnCode) && returnCode.equals(WxPayEnum.RETURN_CODE_OK.getValue())){
                    // TODO: 2018-3-12 获取本地业务订单数据，判断是否已处理
                    // TODO: 2018-3-12 本地未处理订单做状态处理
                }else {
                    //接口通信失败
                    response.init(payNotify.getReturnCode(),payNotify.getReturnMsg());
                }
            }else {
                //签名错误
                response.init(WxPayEnum.RETURN_CODE_FAIL.getValue(),WxPayEnum.BILL_SIGN_ERROR.getValue());
            }
            return WXPayUtil.mapToXml(JSON.parseObject(JSON.toJSONString(response),Map.class));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    @Override
    public WxPayResponse payitilReport(PayitilReport report) {
        WxPayConf wxPayConf = confManager.getWxPayConf();
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5,wxPayConf.isUseSandBox());

        try {
            Map<String,String> result = wxPay.report(JSON.parseObject(JSON.toJSONString(report),Map.class));
            if (validateRequestResult(result)){
                return JSON.parseObject(JSON.toJSONString(report),WxPayResponse.class);
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
