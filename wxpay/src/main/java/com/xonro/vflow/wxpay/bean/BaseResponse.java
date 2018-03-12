package com.xonro.vflow.wxpay.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 通用响应对象
 * @author louie
 * @date created in 2018-3-12 15:55
 */
public class BaseResponse implements Serializable{
    public BaseResponse(){}

    public BaseResponse(String returnCode){
        this.returnCode = returnCode;
    }
    @JSONField(name = "return_code")
    private String returnCode;

    @JSONField(name = "return_msg")
    private String returnMsg;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public void init(String returnCode,String returnMsg){
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

}
