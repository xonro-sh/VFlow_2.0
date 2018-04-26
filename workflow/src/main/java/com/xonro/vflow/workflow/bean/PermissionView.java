package com.xonro.vflow.workflow.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 权限表视图 用于关联查询组信息
 * @author Alex
 * @date 2018/4/25 10:31
 */
@Entity
@Table(name = "v_xr_vflow_permission")
public class PermissionView implements Serializable {
    /**
     * 权限ID
     */
    @Id
    private String id;

    /**
     * 组ID
     */
    private String groupId;

    /**
     * 授权资源类型
     */
    private String resourceType;

    /**
     * 授权资源ID
     */
    private String resourceId;

    /**
     * 扩展字段
     */
    private String ext;

    /**
     * 部门或者角色的名称
     */
    @Column(name = "name_")
    private String name;

    /**
     * 组类型 部门或者角色
     */
    @Column(name = "type_")
    private String groupType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }
}
