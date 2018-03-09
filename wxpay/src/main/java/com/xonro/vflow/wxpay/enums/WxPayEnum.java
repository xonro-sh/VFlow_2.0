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

    /**
     * 交易状态
     */
    TRADE_STATE_SUCCESS("SUCCESS","支付成功"),
    TRADE_STATE_REFUND("REFUND","转入退款"),
    TRADE_STATE_NOTPAY("NOTPAY","未支付"),
    TRADE_STATE_CLOSED("CLOSED","已关闭"),
    TRADE_STATE_REVOKED("REVOKED","已撤销（刷卡支付）"),
    TRADE_STATE_PAYING("USERPAYING","用户支付中 "),
    TRADE_STATE_ERROR("PAYERROR","支付失败(其他原因，如银行返回失败)"),

    /**
     * 微信支付订单关闭错误码
     */
    ORDER_CLOSE_PAID("ORDERPAID","订单已支付，不能发起关单，请当作已支付的正常交易"),
    ORDER_CLOSE_SYSERROR("SYSTEMERROR","系统异常，请重新调用该API"),
    ORDER_CLOSE_CLOSED("ORDERCLOSED","订单已关闭，无需继续调用"),
    ORDER_CLOSE_SIGNERROR("SIGNERROR","参数签名结果不正确"),
    ORDER_CLOSE_POSTMETHODD("REQUIRE_POST_METHOD","请使用post方法"),
    PRDER_CLOSE_XMLERROR("XML_FORMAT_ERROR","XML格式错误"),


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
