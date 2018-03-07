package com.xonro.vflow.wxpay.enums;

/**
 * 微信支付常量枚举
 * @author louie
 * @date created in 2018-3-7 17:17
 */
public enum WxPayEnum {
    /**
     * 接口访问标识
     */
    RETURN_CODE_OK("SUCCESS","微信支付接口通信标识，成功"),
    RETURN_CODE_FAIL("FAIL","微信支付接口通信标识，失败"),
    ;

    private String value;
    private String description;

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    private WxPayEnum(String value,String description){
        this.value = value;
        this.description = description;
    }
}
