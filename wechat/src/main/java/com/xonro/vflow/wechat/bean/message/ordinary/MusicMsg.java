package com.xonro.vflow.wechat.bean.message.ordinary;

import com.alibaba.fastjson.annotation.JSONField;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 音乐消息
 * @author louie
 * @date 2018-2-11
 */
@XStreamAlias("xml")
public class MusicMsg extends OrdinaryMsg{
    /**
     * 音乐标题
     */
    @XStreamAlias("Title")
    @JSONField(name = "Title")
    private String title;

    /**
     * 音乐描述
     */
    @XStreamAlias("Description")
    @JSONField(name = "Description")
    private String description;

    /**
     * 音乐链接
     */
    @XStreamAlias("MusicURL")
    @JSONField(name = "MusicURL")
    private String musicURL;

    /**
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     */
    @XStreamAlias("HQMusicUrl")
    @JSONField(name = "HQMusicUrl")
    private String hqMusicUrl;

    /**
     * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     */
    @XStreamAlias("ThumbMediaId")
    @JSONField(name = "ThumbMediaId")
    private String thumbMediaId;

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

    public String getMusicURL() {
        return musicURL;
    }

    public void setMusicURL(String musicURL) {
        this.musicURL = musicURL;
    }

    public String getHqMusicUrl() {
        return hqMusicUrl;
    }

    public void setHqMusicUrl(String hqMusicUrl) {
        this.hqMusicUrl = hqMusicUrl;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
}
