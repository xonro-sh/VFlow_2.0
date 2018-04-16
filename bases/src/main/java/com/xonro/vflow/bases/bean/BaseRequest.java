package com.xonro.vflow.bases.bean;

/**
 * @author Alex
 * @date 2018/4/12 14:17
 */
public class BaseRequest {
    private boolean ok;
    private String code;
    private String msg;
    private Object data;

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
