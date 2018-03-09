package com.xonro.vflow.wxpay.bean;

import java.io.Serializable;

/**
 * 微信代金券
 * @author louie
 * @date created in 2018-3-9 15:00
 */
public class Coupon implements Serializable{

    /**
     * 代金券类型
     */
    private String couponType;

    /**
     * 代金券id
     */
    private String couponId;

    /**
     * 单个代金券金额
     */
    private String couponFee;

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(String couponFee) {
        this.couponFee = couponFee;
    }
}
