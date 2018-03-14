package com.xonro.vflow.wxpay.web;

import com.xonro.vflow.bases.bean.WxPayConf;
import com.xonro.vflow.bases.helper.ConfManager;
import com.xonro.vflow.wxpay.service.PayService;
import com.xonro.vflow.wxpay.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private PayService payService;

    @Autowired
    private RefundService refundService;
    /**
     * 微信支付结果通知
     * @param notifyData 微信通知数据
     * @return
     */
    @RequestMapping(value = "/pay_notify")
    public String accessPayNotify(@RequestBody String notifyData){
        return payService.accessPayNotify(notifyData);
    }

    /**
     * 退款结果通知
     * @param notifyData 微信通知数据
     * @return
     */
    @RequestMapping(value = "/refund_notify")
    public String accessRefundNotify(@RequestBody String notifyData){
        return refundService.accessRefundNotify(notifyData);
    }

    @RequestMapping(value = "/get_conf")
    public WxPayConf getWxPayConf(){
        return confManager.getWxPayConf();
    }

    @RequestMapping(value = "/save_conf")
    public WxPayConf saveWxPayConf(WxPayConf wxPayConf){
        return confManager.saveWxPayConf(wxPayConf);
    }

}
