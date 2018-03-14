package com.xonro.vflow.wxpay.conf;

import com.github.wxpay.sdk.WXPayConfig;
import com.xonro.vflow.bases.helper.ConfManager;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author louie
 * @Description:
 * @date 2018-3-7 10:26
 */
@Service
public class WxPayConfigImpl implements WXPayConfig {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
        try {
            return FileUtils.openInputStream(new File("apiclient_cert.p12"));
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }
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
