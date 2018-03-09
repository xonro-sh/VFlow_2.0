package com.xonro.vflow.wechat.bean.custom;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author Alex
 * @date 2018/3/6 15:54
 */
public class CustomInfo {
    private String kfAccount;
    private String kfNick;
    private String kfId;
    private String kfHeadimgurl;

    @JSONField(name = "kf_account")
    public String getKfAccount() {
        return kfAccount;
    }

    public void setKfAccount(String kfAccount) {
        this.kfAccount = kfAccount;
    }

    @JSONField(name = "kf_nick")
    public String getKfNick() {
        return kfNick;
    }

    public void setKfNick(String kfNick) {
        this.kfNick = kfNick;
    }

    @JSONField(name = "kf_id")
    public String getKfId() {
        return kfId;
    }

    public void setKfId(String kfId) {
        this.kfId = kfId;
    }

    @JSONField(name = "kf_headimgurl")
    public String getKfHeadimgurl() {
        return kfHeadimgurl;
    }

    public void setKfHeadimgurl(String kfHeadimgurl) {
        this.kfHeadimgurl = kfHeadimgurl;
    }
}
