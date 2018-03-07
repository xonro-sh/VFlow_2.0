package com.xonro.vflow.bases.service;

import com.xonro.vflow.bases.bean.WxPayConf;

/**
 * @author louie
 * @Description: 微信支付基础配置服务接口
 * @date 2018-3-5 15:14
 */
public interface WxPayConfService {

    /**
     * 获取微信支付基础配置
     * @return
     */
    public WxPayConf getWxPayConf();

    /**
     * 保存微信支付基础配置信息
     * @param wxPayConf
     * @return
     */
    public WxPayConf saveWxPayConf(WxPayConf wxPayConf);

}
