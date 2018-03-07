package com.xonro.vflow.wechat.bean.user;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 用户列表数据模型
 * @author Alex
 * @date 2018/2/1
 */
public class UserData {
    /**
     * 列表数据，OPENID的列表
     */
    private List<String> openId;

    @JSONField(name = "openid")
    public List<String> getOpenId() {
        return openId;
    }

    public void setOpenId(List<String> openId) {
        this.openId = openId;
    }

}
