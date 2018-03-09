package com.xonro.vflow.wxpay.web;

import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.wxpay.enums.WxPayEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 微信支付接口调用帮助类
 * @author louie
 * @date created in 2018-3-7 17:27
 */
public class RequestHelper {
    /**
     * 校验微信支付接口通信结果
     * @param resultParams 微信支付接口响应参数
     * @return 通信成功返回true，否则返回false
     */
    public boolean validateRequestResult(Map<String,String> resultParams) throws VFlowException {
        if(!resultParams.isEmpty()){
            String returnCode = resultParams.get("return_code");
            if (StringUtils.isNotEmpty(returnCode) && returnCode.equals(WxPayEnum.RETURN_CODE_OK.getValue())){
                return true;
            }else {
                throw new VFlowException(returnCode,resultParams.get("return_msg"));
            }
        }else {
            throw new VFlowException("FAIL","request wxPay service error,response is empty");
        }
    }

}
