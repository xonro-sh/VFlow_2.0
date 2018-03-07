package com.xonro.vflow.wxpay.conf;

import com.github.wxpay.sdk.WXPayConfig;
import com.xonro.vflow.bases.helper.ConfManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;

/**
 * @author louie
 * @Description:
 * @date 2018-3-7 10:26
 */
public class WxPayConfigImpl implements WXPayConfig {

    @Autowired
    ConfManager confManager;

    @Override
    public String getAppID() {
        return confManager.getWechatConf().getAppId();
    }

    @Override
    public String getMchID() {
        return confManager.getWxPayConf().getMchId();
    }

    @Override
    public String getKey() {
        return confManager.getWxPayConf().getApiKey();
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 5000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 5000;
    }
}
