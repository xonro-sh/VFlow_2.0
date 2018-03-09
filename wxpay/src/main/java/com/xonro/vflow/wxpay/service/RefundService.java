package com.xonro.vflow.wxpay.service;

import com.xonro.vflow.wxpay.bean.refund.Refund;
import com.xonro.vflow.wxpay.bean.refund.RefundResult;

/**
 * 退款业务相关服务接口
 * @author louie
 * @date created in 2018-3-9 14:46
 */
public interface RefundService{

    /**
     * 申请微信退款
     * @param refund
     * @return
     */
    public RefundResult refund(Refund refund);



}
