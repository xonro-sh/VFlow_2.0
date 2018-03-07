package com.xonro.vflow.wechat.bean.message.event;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 上报地理位置事件
 * @author louie
 * @date 2018-2-9
 */
public class LocationEvent extends EventMsg {

    /**
     * 地理位置纬度
     */
    @JSONField(name = "Latitude")
    private String latitude;

    /**
     * 地理位置经度
     */
    @JSONField(name = "Longitude")
    private String Longitude;

    /**
     * 地理位置精度
     */
    @JSONField(name = "Precision")
    private String precision;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }
}
