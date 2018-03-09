package com.xonro.vflow.wechat.bean.message.ordinary;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 地理位置消息
 * @author louie
 * @date 2018-2-8
 */
public class LocationMsg extends OrdinaryMsg {
    /**
     * 地理位置维度
     */
    @JSONField(name = "Location_X")
    private String locationX;

    /**
     * 地理位置经度
     */
    @JSONField(name = "Location_Y")
    private String locationY;

    /**
     * 地图缩放大小
     */
    @JSONField(name = "Scale")
    private String scale;

    /**
     * 地理位置信息
     */
    @JSONField(name = "Label")
    private String label;

    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
