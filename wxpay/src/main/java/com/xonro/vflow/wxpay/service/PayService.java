package com.xonro.vflow.wxpay.service;

import com.xonro.vflow.wxpay.bean.WxPayResponse;
import com.xonro.vflow.wxpay.bean.pay.PayitilReport;

/**
 * 交易相关业务服务接口
 * @author louie
 * @date created in 2018-3-12 14:56
 */
public interface PayService {

    /**
     * 接收支付通知并处理
     * @param notifyData
     * @return
     */
    public String accessPayNotify(String notifyData);

    /**
     * 微信接口调用数据上报
     * @param report
     * @return
     */
    public WxPayResponse payitilReport(PayitilReport report);
}
