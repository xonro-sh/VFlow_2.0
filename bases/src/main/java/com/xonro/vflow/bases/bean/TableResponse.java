package com.xonro.vflow.bases.bean;


import java.io.Serializable;

/**
 * 表格数据返回
 * @author Alex
 * @date 2018/1/23
 */
public class TableResponse implements Serializable{
    private Integer code;
    private String msg;
    private Long count;
    private Object data;

    public TableResponse() {
    }

    public TableResponse(Integer code, String msg, long count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
