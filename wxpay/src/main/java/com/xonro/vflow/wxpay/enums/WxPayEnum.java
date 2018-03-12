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
    ORDER_CLOSE_XMLERROR("XML_FORMAT_ERROR","XML格式错误"),

    /**
     * 微信退款订单查询支付错误码
     */
    REFUND_ERROR_SYSERROR("SYSTEMERROR","系统超时"),
    REFUND_REFUND_NOT_EXIT("REFUNDNOTEXIST","订单号错误或订单状态不正确"),
    REFUND_INVALID_TRANSACTIONID("INVALID_TRANSACTIONID","无效transaction_id,检查原交易号是否存在或发起支付交易接口返回失败"),
    REFUND_PARAM_ERROR("PARAM_ERROR","请求参数错误"),
    REFUND_APPID_NOTEXIT("APPID_NOT_EXIST","APPID不存在"),
    REFUND_MCHID_NOTEXIT("MCHID_NOT_EXIST","MCHID不存在"),
    REFUND_REQUIRE_POST("REQUIRE_POST_METHOD","请使用post方法"),
    REFUND_SIGN_ERROR("SIGNERROR","参数签名结果不正确"),
    REFUND_XML_FORMAT_ERROR("XML_FORMAT_ERROR","XML格式错误"),

    /**
     * 微信支付下载对账单错误码
     */
    BILL_SYSTEM_ERROR("SYSTEMERROR","下载失败"),
    BILL_INVALID_BILLTYPE("invalid bill_type","订单类型错误"),
    BILL_DATA_FORMAT_ERROR("data format error","数据格式错误"),
    BILL_MISS_PARAM("missing parameter","缺少参数"),
    BILL_SIGN_ERROR("SIGN ERROR","签名错误"),
    BILL_NOT_EXIT("NO BillDownload Exist","账单不存在,请先检查当前商户号在指定日期内是否有成功的交易"),
    BILL_CREATING("BillDownload Creating","账单未生成,请先检查当前商户号在指定日期内是否有成功的交易"),
    BILL_COMPRESS_GZIPERR("CompressGZip Error","账单压缩失败"),
    BILL_UNCOMP_GZIPERR("UnCompressGZip Error","账单解压失败"),

    /**
     * 账单类型
     */
    BILL_TYPE_ALL("ALL","返回当日所有订单信息，默认值"),
    BILL_TYPE_SUCCESS("SUCCESS","返回当日成功支付的订单"),
    BILL_TYPE_REFUND("REFUND","返回当日退款订单"),
    BILL_TYPE_RECHARGE_REFUND("RECHARGE_REFUND","返回当日充值退款订单"),

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
