package com.xonro.vflow.bases.exception;

/**
 * @author louie
 * @Description: vflow自定义异常
 * @date 2018-3-5 17:59
 */
public class VFlowException extends Exception {
    private String errorCode;
    private String message;

    public VFlowException(){
        super();
    }

    public VFlowException(String errorCode,String message){
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
