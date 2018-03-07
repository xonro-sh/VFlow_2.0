package com.xonro.vflow.wechat.bean.menu;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 跳转图文消息URL模型
 * @author Alex
 * @date 2018/1/16
 */
public class ViewLimitedMenu {
    /**
     * 	菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
     */
    private String type;
    /**
     * 	菜单标题，不超过16个字节，子菜单不超过60个字节
     */
    private String name;
    /**
     * 调用新增永久素材接口返回的合法media_id
     */
    private String mediaId;
    /**
     * 二级菜单数组，个数应为1~5个
     */
    private List<Object> subButton;

    public ViewLimitedMenu(String name, String mediaId) {
        this.type = "view_limited";
        this.name = name;
        this.mediaId = mediaId;
    }

    public ViewLimitedMenu(String name, List<Object> subButton) {
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
    @JSONField(name = "media_id")
    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
    @JSONField(name = "sub_button")
    public List<Object> getSubButton() {
        return subButton;
    }

    public void setSubButton(List<Object> subButton) {
        this.subButton = subButton;
    }
}
