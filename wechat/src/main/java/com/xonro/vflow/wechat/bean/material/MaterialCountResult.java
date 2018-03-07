package com.xonro.vflow.wechat.bean.material;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author Alex
 * @date 2018/1/18
 */
public class MaterialCountResult {
    private String errCode;
    private String errMsg;
    /**
     * 语音总数量
     */
    private String voiceCount;
    /**
     * 视频总数量
     */
    private String videoCount;
    /**
     * 图片总数量
     */
    private String imageCount;
    /**
     * 图文总数量
     */
    private String newsCount;

    @JSONField(name = "errcode")
    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    @JSONField(name = "errmsg")
    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @JSONField(name = "voice_count")
    public String getVoiceCount() {
        return voiceCount;
    }

    public void setVoiceCount(String voiceCount) {
        this.voiceCount = voiceCount;
    }

    @JSONField(name = "video_count")
    public String getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(String videoCount) {
        this.videoCount = videoCount;
    }

    @JSONField(name = "image_count")
    public String getImageCount() {
        return imageCount;
    }

    public void setImageCount(String imageCount) {
        this.imageCount = imageCount;
    }

    @JSONField(name = "news_count")
    public String getNewsCount() {
        return newsCount;
    }

    public void setNewsCount(String newsCount) {
        this.newsCount = newsCount;
    }
}
