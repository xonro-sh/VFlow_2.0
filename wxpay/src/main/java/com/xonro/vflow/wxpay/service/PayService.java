package com.xonro.vflow.wxpay.service;

import com.xonro.vflow.wxpay.bean.WxPayResponse;
import com.xonro.vflow.wxpay.bean.pay.DownloadFundFlow;
import com.xonro.vflow.wxpay.bean.pay.PayitilReport;

import java.util.List;

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
    WxPayResponse accessPayNotify(String notifyData) throws Exception;

    /**
     * 微信接口调用数据上报
     * @param report
     * @return
     */
    WxPayResponse payitilReport(PayitilReport report);

    /**
     * 下载资金流水
     * @param fundFlow
     * @return WxPayResponse
     *  接口访问结果，result_code为success时，流水数据为data属性值
     */
    WxPayResponse downloadFundFlow(DownloadFundFlow fundFlow);

    /**
     * 解析微信的业务数据，如账单、订单评价、资金流水
     * @param wxData
     * @return 解析后的数据集，List<List>为行，List<String>为每行的行数据
     */
    List<List<String>> parseWxData(String wxData);
}
