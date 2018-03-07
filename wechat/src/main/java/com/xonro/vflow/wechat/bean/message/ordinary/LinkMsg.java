package com.xonro.vflow.wechat.bean.message.ordinary;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 链接消息
 * @author louie
 * @date 2018-2-8
 */
public class LinkMsg extends OrdinaryMsg {

    /**
     * 消息标题
     */
    @JSONField(name = "Title")
    private String title;

    /**
     * 消息描述
     */
    @JSONField(name = "Description")
    private String description;

    /**
     * 消息链接
     */
    @JSONField(name = "Url")
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
