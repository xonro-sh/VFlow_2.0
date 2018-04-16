package com.xonro.vflow.bases.bean;

/**
 * 请求基础响应类
 * @author Alex
 * @date 2018/1/23
 */
public class BaseResponse {
    private boolean ok;
    private String code;
    private String msg;
    private Object data;

    public BaseResponse() {
        super();
    }

    public BaseResponse(boolean ok,Object data){
        this.ok = ok;
        this.data = data;
    }

    public BaseResponse(boolean ok, String code, String msg) {
        this.ok = ok;
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(boolean ok, String code, String msg, Object data) {
        this.ok = ok;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
