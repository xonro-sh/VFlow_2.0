package com.xonro.vflow.bases.bean;

import java.util.List;

/**
 * @author Alex
 * @date 2018/5/8 15:52
 */
public class JsTreeResponse {
    /**
     * 节点名称
     */
    private String text;
    /**
     * 标识
     */
    private String id;
    /**
     * 子节点list
     */
    private List<JsTreeResponse> children;

    public JsTreeResponse(String text, String id, List<JsTreeResponse> children) {
        this.text = text;
        this.id = id;
        this.children = children;
    }

    public JsTreeResponse(String text, String id) {
        this.text = text;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<JsTreeResponse> getChildren() {
        return children;
    }

    public void setChildren(List<JsTreeResponse> children) {
        this.children = children;
    }
}
