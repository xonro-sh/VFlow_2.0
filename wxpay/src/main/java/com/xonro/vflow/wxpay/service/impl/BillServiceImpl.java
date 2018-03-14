package com.xonro.vflow.wxpay.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.xonro.vflow.bases.bean.WxPayConf;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.bases.helper.ConfManager;
import com.xonro.vflow.wxpay.bean.WxPayResponse;
import com.xonro.vflow.wxpay.bean.bill.QueryComment;
import com.xonro.vflow.wxpay.helper.ServiceRequestHelper;
import com.xonro.vflow.wxpay.service.BillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author louie
 * @date created in 2018-3-12 10:06
 */
@Service
public class BillServiceImpl extends ServiceRequestHelper implements BillService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConfManager confManager;

    @Autowired
    private WXPayConfig wxPayConfig;

    @Override
    public WxPayResponse billDownload(String billDate,String billType,String tarType) {
        WxPayConf wxPayConf = confManager.getWxPayConf();
        WXPay wxPay = new WXPay(wxPayConfig, WXPayConstants.SignType.MD5,wxPayConf.isUseSandBox());

        try {
            Map<String,String> result = wxPay.downloadBill(
                    new HashMap<String, String>(8){{
                      put("bill_date",billDate);
                      put("bill_type",billType);
                      put("tar_type",tarType);
                    }}
            );
            if (validateRequestResult(result)){
                return JSON.parseObject(JSON.toJSONString(result),WxPayResponse.class);
            }
        } catch (VFlowException e){
            logger.error(e.getMessage(),e);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    @Override
    public WxPayResponse batchQueryComment(QueryComment queryComment) {
        WxPayConf wxPayConf = confManager.getWxPayConf();
        try {
            Map<String,String> result = batchQueryComment(
                    wxPayConfig,
                    JSON.parseObject(JSON.toJSONString(queryComment),Map.class),
                    wxPayConf.isUseSandBox()
            );
            if (validateRequestResult(result)){
               return JSON.parseObject(JSON.toJSONString(result), WxPayResponse.class);
            }
            return JSON.parseObject(JSON.toJSONString(result),WxPayResponse.class);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }
}
