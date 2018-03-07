package com.xonro.vflow.bases.helper;

import com.xonro.vflow.bases.bean.WechatConf;
import com.xonro.vflow.bases.bean.WxPayConf;
import com.xonro.vflow.bases.service.WechatConfService;
import com.xonro.vflow.bases.service.WxPayConfService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author louie
 * @Description: 配置管理器，用于获取、更新配置等操作
 * @date 2018-3-2 16:53
 */

@Service
public class ConfManager {

    @Autowired
    private WechatConfService wechatConfService;

    @Autowired
    private WxPayConfService wxPayConfService;

    /**
     * 获取公众号配置信息
     * @return
     */
    public WechatConf getWechatConf(){
        return wechatConfService.getWechatConf();
    }

    /**
     * 更新公众号配置信息
     * @param wechatConf
     */
    public WechatConf saveWechatConf(WechatConf wechatConf){
        WechatConf oldConf = wechatConfService.getWechatConf();

        //若已存在企业号配置，则执行更新操作
        if (oldConf != null && StringUtils.isNotEmpty(oldConf.getId())){
            wechatConf.setId(oldConf.getId());
        }
        return wechatConfService.saveWechatConf(wechatConf);
    }

    /**
     * 获取微信支付基础配置
     * @return
     */
    public WxPayConf getWxPayConf(){
        return wxPayConfService.getWxPayConf();
    }

    /**
     * 保存微信支付基础配置
     * @param wxPayConf
     * @return
     */
    public WxPayConf saveWxPayConf(WxPayConf wxPayConf){
        WxPayConf oldConf = wxPayConfService.getWxPayConf();
        if (oldConf != null && StringUtils.isNotEmpty(oldConf.getId())){
            wxPayConf.setId(oldConf.getId());
        }

        return wxPayConfService.saveWxPayConf(wxPayConf);
    }

}
