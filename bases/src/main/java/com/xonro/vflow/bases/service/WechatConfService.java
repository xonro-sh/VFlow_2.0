package com.xonro.vflow.bases.service;

import com.xonro.vflow.bases.bean.WechatConf;

/**
 * @author louie
 * @Description:微信公众号配置服务
 * @date 2018-3-2 11:36
 */
public interface WechatConfService {

    /**
     * 获取微信公众号配置信息
     * @return
     */
    public WechatConf getWechatConf();

    /**
     * 保存微信公众号配置
     * @param wechatConf
     */
    public WechatConf saveWechatConf(WechatConf wechatConf);


}
