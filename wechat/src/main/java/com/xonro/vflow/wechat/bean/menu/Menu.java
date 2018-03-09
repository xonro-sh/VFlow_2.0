package com.xonro.vflow.wechat.bean.menu;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 菜单模型(点击 扫码用)
 * @author Alex
 * @date 2018/1/16
 */
public class Menu {
    /**
     * 	菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
     */
    private String type;
    /**
     * 	菜单标题，不超过16个字节，子菜单不超过60个字节
     */
    private String name;
    /**
     * 菜单KEY值，用于消息接口推送，不超过128字节
     */
    private String key;
    /**
     * 二级菜单数组，个数应为1~5个
     */
    private List<Object> subButton;

    public Menu(String type, String name, String key) {
        this.type = type;
        this.name = name;
        this.key = key;
    }

    public Menu(String name, List<Object> subButton) {
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @JSONField(name = "sub_button")
    public List<Object> getSubButton() {
        return subButton;
    }

    public void setSubButton(List<Object> subButton) {
        this.subButton = subButton;
    }
}
