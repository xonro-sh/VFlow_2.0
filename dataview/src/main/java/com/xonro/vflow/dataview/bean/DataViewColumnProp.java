package com.xonro.vflow.dataview.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 列属性bean
 * @author Alex
 * @date 2018/3/15 21:14
 */
public class DataViewColumnProp implements Serializable{
    /**
     * 列名称
     */
    private String name;
    /**
     * 列标题
     */
    private String title;
    /**
     * 分组
     */
    private String group;
    /**
     * 统计类型
     */
    private String statisticalType;

    @JSONField(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JSONField(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JSONField(name = "group")
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @JSONField(name = "statistical_type")
    public String getStatisticalType() {
        return statisticalType;
    }

    public void setStatisticalType(String statisticalType) {
        this.statisticalType = statisticalType;
    }
}
