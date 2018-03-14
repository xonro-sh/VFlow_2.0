package com.xonro.vflow.wxpay.bean.bill;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 拉取订单评价数据
 * @author louie
 * @date created in 2018-3-13 17:45
 */
public class QueryComment implements Serializable{
    /**
     * 开始时间
     */
    @JSONField(name = "begin_time")
    private String beginTime;

    /**
     * 结束时间
     */
    @JSONField(name = "end_time")
    private String endTime;

    /**
     * 位移:指定从某条记录的下一条开始返回记录
     */
    @JSONField(name = "offset")
    private Integer offset;

    /**
     * 一次拉取的条数, 最大值是200，默认是200
     */
    @JSONField(name = "limit")
    private Integer limit;

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
