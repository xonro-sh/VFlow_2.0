package com.xonro.vflow.wxpay.web.controller;

import com.xonro.vflow.bases.bean.WxPayConf;
import com.xonro.vflow.bases.helper.ConfManager;
import com.xonro.vflow.wxpay.bean.bill.Bill;
import com.xonro.vflow.wxpay.bean.order.QueryOrderResult;
import com.xonro.vflow.wxpay.web.WebConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信支付web控制器
 * @author louie
 * @date created in 2018-3-7 17:47
 */
@RestController
@RequestMapping(value = "/wxpay")
public class WxPayController {

    @Autowired
    private ConfManager confManager;

    @Autowired
    private WebConverter webConverter;

    /**
     * 微信支付结果通知
     * @param notifyData 微信通知数据
     * @return
     */
    @RequestMapping(value = "/pay_notify")
    public String accessPayNotify(@RequestBody String notifyData){
        return webConverter.accessPayNotify(notifyData);
    }

    /**
     * 退款结果通知
     * @param notifyData 微信通知数据
     * @return
     */
    @RequestMapping(value = "/refund_notify")
    public String accessRefundNotify(@RequestBody String notifyData){
        return webConverter.accessRefundNotify(notifyData);
    }

    @RequestMapping(value = "/get_conf")
    public WxPayConf getWxPayConf(){
        return confManager.getWxPayConf();
    }

    @RequestMapping(value = "/save_conf",method = RequestMethod.POST)
    public WxPayConf saveWxPayConf(WxPayConf wxPayConf){
        return confManager.saveWxPayConf(wxPayConf);
    }

    /**
     * 分页查询对账单
     * @param billDate 对账单日期
     * @param perPageNumber 每页行数
     * @param pageNow 当前页码
     * @return
     */
    @RequestMapping(value = "/query_bill",method = RequestMethod.POST)
    public Page<Bill> getAllBillByPageAndDate(String billDate,Integer perPageNumber,Integer pageNow){
        return webConverter.getAllBillByPageAndDate(billDate,pageNow,perPageNumber);
    }

    /**
     * 根据订单号查询微信订单
     * @param transactionId
     * @return
     */
    @RequestMapping(value = "/query_order")
    public QueryOrderResult queryOrderByTransactionId(String transactionId){
        return webConverter.queryOrderByTransactionId(transactionId);
    }
}
