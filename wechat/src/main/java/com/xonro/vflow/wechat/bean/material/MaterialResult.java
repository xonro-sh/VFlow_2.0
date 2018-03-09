package com.xonro.vflow.wechat.bean.material;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 素材结果模型
 * @author Alex
 * @date 2018/1/17
 */
public class MaterialResult {
    private String errCode;
    private String errMsg;
    /**
     * 	媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb，主要用于视频与音乐格式的缩略图）
     */
    private String type;
    /**
     * 媒体文件上传后，获取标识
     */
    private String mediaId;
    /**
     * 媒体文件上传时间戳
     */
    private String createdAt;
    /**
     * 	新增的图片素材的图片URL（仅新增图片素材时会返回该字段）
     */
    private String url;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
