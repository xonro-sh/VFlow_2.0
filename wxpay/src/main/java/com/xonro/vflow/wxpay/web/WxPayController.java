package com.xonro.vflow.wxpay.web;

import com.xonro.vflow.wxpay.service.PayService;
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
    private PayService payService;

    /**
     * 微信支付结果通知
     * @param notifyData 微信通知数据
     * @return
     */
    @RequestMapping(value = "/pay_notify")
    public String accessPayNotify(@RequestBody String notifyData){
        return payService.accessPayNotify(notifyData);
    }


}
