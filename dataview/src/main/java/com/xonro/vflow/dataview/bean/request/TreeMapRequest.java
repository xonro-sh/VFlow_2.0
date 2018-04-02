package com.xonro.vflow.dataview.bean.request;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author Alex
 * @date 2018/3/29 14:41
 */
public class TreeMapRequest implements Serializable{
    /**
     * 父数据字段名
     */
    private String parentName;
    /**
     * 子数据字段名
     */
    private String name;
    /**
     * 显示名称
     */
    private String showName;
    /**
     * 值
     */
    private String value;
    /**
     * 跟节点条件
     */
    private String rootNodeCondition;

    @JSONField(name = "parent_name")
    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @JSONField(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JSONField(name = "show_name")
    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    @JSONField(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @JSONField(name = "root_node_condition")
    public String getRootNodeCondition() {
        return rootNodeCondition;
    }

    public void setRootNodeCondition(String rootNodeCondition) {
        this.rootNodeCondition = rootNodeCondition;
    }
}
