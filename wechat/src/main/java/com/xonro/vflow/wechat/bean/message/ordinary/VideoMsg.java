package com.xonro.vflow.wechat.bean.message.ordinary;

import com.alibaba.fastjson.annotation.JSONField;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 视频消息/小视频消息
 * @author louie
 * @date 2018-2-8
 */
@XStreamAlias("xml")
public class VideoMsg extends OrdinaryMsg {
    /**
     * 视频消息媒体id
     */
    @JSONField(name = "MediaId")
    @XStreamAlias("MediaId")
    private String mediaId;

    /**
     * 视频消息缩略图的媒体id
     */
    @JSONField(name = "ThumbMediaId")
    private String thumbMediaId;

    /**
     * 视频消息的标题,回复用户消息时使用
     */
    @XStreamAlias("Title")
    private String title;

    /**
     * 视频消息的描述,回复用户消息时使用
     */
    @XStreamAlias("Description")
    private String description;

    public VideoMsg(String mediaId,String thumbMediaId){
        this.mediaId = mediaId;
        this.thumbMediaId = thumbMediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

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
}
