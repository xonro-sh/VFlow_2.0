package com.xonro.vflow.wechat.bean.message.ordinary;

import com.alibaba.fastjson.annotation.JSONField;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 语音消息
 * @author louie
 * @date 2018-2-8
 */
@XStreamAlias("xml")
public class VoiceMsg extends OrdinaryMsg {
    /**
     * 语音消息媒体id
     */
    @JSONField(name = "MediaId")
    @XStreamAlias("MediaId")
    private String mediaId;

    /**
     * 语音格式，如amr，speex等
     */
    @JSONField(name = "Format")
    private String format;

    /**
     * 语音识别结果，UTF8编码
     * 需开启语音识别可获得
     */
    @JSONField(name = "Recognition")
    private String recognition;

    public VoiceMsg(String mediaId,String format,String recognition){
        this.mediaId = mediaId;
        this.format = format;
        this.recognition = recognition;
    }

    public String getMediaId() {
        return mediaId;
    }

    public String getFormat() {
        return format;
    }

    public String getRecognition() {
        return recognition;
    }
}
