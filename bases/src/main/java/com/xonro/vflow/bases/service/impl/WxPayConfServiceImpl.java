package com.xonro.vflow.bases.service.impl;

import com.xonro.vflow.bases.bean.WxPayConf;
import com.xonro.vflow.bases.dao.WxPayConfRepository;
import com.xonro.vflow.bases.service.WxPayConfService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author louie
 * @Description:
 * @date 2018-3-5 15:24
 */
@Service
public class WxPayConfServiceImpl implements WxPayConfService {
    @Resource
    private WxPayConfRepository confRepository;

    @Value("${xonro.vflow.server_host}")
    private String serverHost;

    @Override
    @Cacheable(value = "wxpay",key = "'wxpay_configuration'",unless = "#result eq null ")
    public WxPayConf getWxPayConf() {
        return confRepository.findFirstByIdIsNotNull();
    }

    @Override
    @CachePut(value = "wxpay",key = "'wxpay_configuration'",unless = "#result eq null ")
    public WxPayConf saveWxPayConf(WxPayConf wxPayConf) {
        wxPayConf.setNotifyUrl(serverHost+"/wxpay/pay_notify");
        wxPayConf.setRefundNotifyUrl(serverHost+"/wxpay/refund_notify");
        return confRepository.save(wxPayConf);
    }
}
