package com.xonro.vflow.wechat.bean.massmessage;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author Alex
 * @date 2018/1/15
 */
public class MassSpeedResult {
    /**
     * 群发速度的级别
     */
    private Integer speed;
    /**
     * 群发速度的真实值 单位：万/分钟
     */
    private Integer realspeed;
    @JSONField(name = "speed")
    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
    @JSONField(name = "realspeed")
    public Integer getRealspeed() {
        return realspeed;
    }

    public void setRealspeed(Integer realspeed) {
        this.realspeed = realspeed;
    }
}
