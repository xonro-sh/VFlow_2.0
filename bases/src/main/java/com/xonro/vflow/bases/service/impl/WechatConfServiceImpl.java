package com.xonro.vflow.bases.service.impl;

import com.xonro.vflow.bases.bean.WechatConf;
import com.xonro.vflow.bases.dao.WechatConfRepository;
import com.xonro.vflow.bases.service.WechatConfService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author louie
 * @Description: 微信企业号配置服务实现
 * @date 2018-3-2 16:24
 */
@Service
public class WechatConfServiceImpl implements WechatConfService {

    @Resource
    private WechatConfRepository confRepository;

    @Value("${xonro.vflow.server_host}")
    private String serverHost;

    @Override
    @Cacheable(value = "wechat",key = "'wecaht_configuration'",unless = "#result eq null")
    public WechatConf getWechatConf() {
       return confRepository.findDistinctFirstByIdIsNotNull();
    }

    @Override
    @CachePut(value = "wechat",key = "'wecaht_configuration'",unless = "#result eq null")
    public WechatConf saveWechatConf(WechatConf wechatConf) {
        wechatConf.setCallBackUrl(serverHost+"/wechat/access");
        return confRepository.save(wechatConf);
    }
}
