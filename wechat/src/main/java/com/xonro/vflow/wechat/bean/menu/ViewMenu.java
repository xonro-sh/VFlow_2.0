package com.xonro.vflow.wechat.bean.menu;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @author Alex
 * @date 2018/1/16
 */
public class ViewMenu {
    /**
     * 	菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
     */
    private String type;
    /**
     * 	菜单标题，不超过16个字节，子菜单不超过60个字节
     */
    private String name;
    /**
     * 网页 链接，用户点击菜单可打开链接，不超过1024字节。 type为miniprogram时，不支持小程序的老版本客户端将打开本url。
     */
    private String url;
    /**
     * 二级菜单数组，个数应为1~5个
     */
    private List<Object> subButton;

    public ViewMenu(String name, String url) {
        this.type = "view";
        this.name = name;
        this.url = url;
    }

    public ViewMenu(String name, List<Object> subButton) {
        this.name = name;
        this.subButton = subButton;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JSONField(name = "sub_button")
    public List<Object> getSubButton() {
        return subButton;
    }

    public void setSubButton(List<Object> subButton) {
        this.subButton = subButton;
    }
}
