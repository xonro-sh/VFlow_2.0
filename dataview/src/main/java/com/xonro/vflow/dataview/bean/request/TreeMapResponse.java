package com.xonro.vflow.dataview.bean.request;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alex
 * @date 2018/3/29 14:57
 */
public class TreeMapResponse implements Serializable {
    private String name;
    private String value;
    private List<TreeMapResponse> treeMapResponses;
    private String no;
    private String pno;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @JSONField(name = "children")
    public List<TreeMapResponse> getTreeMapResponses() {
        return treeMapResponses;
    }

    public void setTreeMapResponses(List<TreeMapResponse> treeMapResponses) {
        this.treeMapResponses = treeMapResponses;
    }

    @JSONField(serialize = false)
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @JSONField(serialize = false)
    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }
}
