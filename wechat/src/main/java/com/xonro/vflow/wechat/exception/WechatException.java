package com.xonro.vflow.wechat.exception;

/**
 * 微信公众平台服务自定义异常
 * @author louie
 * @date 2018-1-12
 */
public class WechatException extends Exception{
    private String errorCode;
    private String message;

    public WechatException(){
        super();
    }

    public WechatException(String errorCode, String message){
        super(errorCode);
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return "errorCode:"+errorCode+",errorMsg:"+message;
    }
}
