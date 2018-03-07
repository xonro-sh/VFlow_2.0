package com.xonro.vflow.wechat.bean.message.ordinary;

import com.alibaba.fastjson.annotation.JSONField;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 图片消息
 * @author louie
 * @date 2018-2-8
 */
@XStreamAlias("xml")
public class ImageMsg extends OrdinaryMsg {
    /**
     * 图片链接
     */
    @JSONField(name = "PicUrl")
    @XStreamAlias("PicUrl")
    private String picUrl;

    /**
     * 图片消息媒体id
     */
    @JSONField(name = "MediaId")
    @XStreamAlias("MediaId")
    private String mediaId;

    public String getPicUrl() {
        return picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public String toString() {
        return "ImageMsg{" +
                "picUrl='" + picUrl + '\'' +
                ", mediaId='" + mediaId + '\'' +
                '}';
    }
}
