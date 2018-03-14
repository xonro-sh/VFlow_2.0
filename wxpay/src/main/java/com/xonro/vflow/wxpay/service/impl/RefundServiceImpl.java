package com.xonro.vflow.wxpay.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.xonro.vflow.bases.bean.WxPayConf;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.bases.helper.ConfManager;
import com.xonro.vflow.wxpay.bean.WxPayResponse;
import com.xonro.vflow.wxpay.bean.refund.QueryRefundResult;
import com.xonro.vflow.wxpay.bean.refund.Refund;
import com.xonro.vflow.wxpay.bean.refund.RefundNotify;
import com.xonro.vflow.wxpay.bean.refund.RefundResult;
import com.xonro.vflow.wxpay.enums.WxPayEnum;
import com.xonro.vflow.wxpay.helper.ServiceRequestHelper;
import com.xonro.vflow.wxpay.service.RefundService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
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

    @Override
    public QueryRefundResult queryRefundByTradeId(String tradeId) {
        WxPayConf wxPayConf = confManager.getWxPayConf();
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5,wxPayConf.isUseSandBox());

        try {
            Map<String,String> params = new HashMap<String, String>(4){{
                put("transaction_id",tradeId);
            }};
            return queryRefund(wxPay,params);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    @Override
    public QueryRefundResult queryRefundByOutTradeId(String outTradeId) {
        WxPayConf wxPayConf = confManager.getWxPayConf();
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5,wxPayConf.isUseSandBox());

        try {
            Map<String,String> params = new HashMap<String, String>(4){{
                put("out_trade_no",outTradeId);
            }};
            return queryRefund(wxPay,params);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    @Override
    public QueryRefundResult queryRefundByRefundId(String refundId) {
        WxPayConf wxPayConf = confManager.getWxPayConf();
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5,wxPayConf.isUseSandBox());

        try {
            Map<String,String> params = new HashMap<String, String>(4){{
                put("refund_id",refundId);
            }};
            return queryRefund(wxPay,params);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    @Override
    public QueryRefundResult queryRefundByOutRefundId(String outRefundId) {
        WxPayConf wxPayConf = confManager.getWxPayConf();
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5,wxPayConf.isUseSandBox());

        try {
            Map<String,String> params = new HashMap<String, String>(4){{
                put("out_refund_no",outRefundId);
            }};
            return queryRefund(wxPay,params);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    @Override
    public WxPayResponse accessRefundNotify(String notifyXml) {
        WxPayResponse response = new WxPayResponse(WxPayEnum.RETURN_CODE_OK.getValue());
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5);
        WxPayConf wxPayConf = confManager.getWxPayConf();

        try {
            Map<String,String> notifyData = WXPayUtil.xmlToMap(notifyXml);
            //进行签名和通信是否正确的校验
            if (wxPay.isPayResultNotifySignatureValid(notifyData)){
                String returnCode = notifyData.get("return_code");
                if (StringUtils.isNotEmpty(returnCode) && returnCode.equals(WxPayEnum.RETURN_CODE_OK.getValue())){
                    //微信推送的加密信息,需解密
                    String reqInfo = notifyData.get("req_info");
                    byte[] base64Decoded = new Base64().decode(reqInfo);
                    Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
                    cipher.init(
                            Cipher.DECRYPT_MODE,
                            new SecretKeySpec(DigestUtils.md5Hex(wxPayConf.getApiKey()).toLowerCase().getBytes(),KEY_ALGORITHM)
                    );
                    String decodedData = new String(cipher.doFinal(base64Decoded),"utf-8");
                    Map<String,String> params = WXPayUtil.xmlToMap(decodedData);

                    RefundNotify refundNotify = JSON.parseObject(JSON.toJSONString(params),RefundNotify.class);

                    // TODO: 2018-3-13 判断业务数据状态，若业务数据已做处理，则返回成功
                    // TODO: 2018-3-13 若业务数据未作处理，则处理相应业务数据

                }else {
                    //微信通知接口通信错误
                    response.setResult(returnCode,notifyData.get("return_msg"));
                    logger.error("wxpay refund notify error,return_code:"+returnCode+",return_msg:"+notifyData.get("return_msg"));
                }
            }else {
                //微信通知签名校验错误
                response.setResult(WxPayEnum.RETURN_CODE_FAIL.getValue(),WxPayEnum.BILL_SIGN_ERROR.getValue());
                logger.error("wxpay refund notify sign error");
            }
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }
}
