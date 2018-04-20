package com.xonro.vflow.bases.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alex
 * @date 2018/1/26
 */
public class NodeResponse implements Serializable{
    /**
     * 节点名称
     */
    private String name;
    /**
     * 标识
     */
    private String id;
    /**
     * 是否展开状态（默认false）
     */
    private boolean spread;
    /**
     * 子节点list
     */
    private List<NodeResponse> children;

    /**
     * 父节点必须传参数
     * @param name 节点名称
     * @param id 标识
     * @param spread 是否展开状态（默认false）
     * @param children 子节点list
     */
    public NodeResponse(String name, String id, boolean spread, List<NodeResponse> children) {
        this.name = name;
        this.id = id;
        this.spread = spread;
        this.children = children;
    }

    /**
     * 某级子节点必须传的参数
     * @param name 节点名称
     * @param id 标识
     */
    public NodeResponse(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public NodeResponse(String name, String id, List<NodeResponse> children) {
        this.name = name;
        this.id = id;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public List<NodeResponse> getChildren() {
        return children;
    }

    public void setChildren(List<NodeResponse> children) {
        this.children = children;
    }
}
