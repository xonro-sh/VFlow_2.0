package com.xonro.vflow.wechat.bean.custom;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 图文消息实体
 * @author Alex
 * @date 2018/3/6 15:35
 */
public class CustomArticlesMessage {
    private String title;
    private String description;
    private String url;
    private String picurl;

    /**
     * 图文消息实体
     * @param title 标题
     * @param description 描述
     * @param url 图文消息被点击后跳转的链接
     * @param picurl 图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80
     */
    public CustomArticlesMessage(String title, String description, String url, String picurl) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.picurl = picurl;
    }

    @JSONField(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @JSONField(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @JSONField(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @JSONField(name = "picurl")
    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }
}
