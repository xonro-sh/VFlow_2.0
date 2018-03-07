package com.xonro.vflow.wxpay.conf;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.InputStream;

/**
 * @author louie
 * @Description:
 * @date 2018-3-7 10:26
 */
public class WxPayConfigImpl implements WXPayConfig {
    @Override
    public String getAppID() {
        return null;
    }

    @Override
    public String getMchID() {
        return null;
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 0;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 0;
    }
}
