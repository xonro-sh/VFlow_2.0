package com.xonro.vflow.portal.bean;


import java.io.Serializable;
import java.util.List;

/**
 * @author Alex
 * @date 2018/5/4 17:13
 */
public class PortalResponse implements Serializable{
    public PortalResponse(String name, String id, String url, String icon, List<PortalResponse> children) {
        this.name = name;
        this.id = id;
        this.url = url;
        this.icon = icon;
        this.children = children;
    }

    /**
     * 节点名称
     */
    private String name;
    /**
     * 标识
     */
    private String id;

    /**
     * 链接
     */
    private String url;

    /**
     * 节点icon
     */
    private String icon;
    /**
     * 子节点list
     */
    private List<PortalResponse> children;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<PortalResponse> getChildren() {
        return children;
    }

    public void setChildren(List<PortalResponse> children) {
        this.children = children;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
