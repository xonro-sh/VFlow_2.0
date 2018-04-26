package com.xonro.vflow.workflow.bean;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Alex
 * @date 2018/4/25 9:35
 */
@Entity
@Table(name = "b_xr_vflow_permission")
public class Permission implements Serializable {
    /**
     * 权限ID
     */
    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    /**
     * 组ID
     */
    @NotBlank(message = "groupId can't be empty")
    private String groupId;

    /**
     * 授权资源类型
     */
    @NotBlank(message = "resourceType can't be empty")
    private String resourceType;

    /**
     * 授权资源ID
     */
    @NotBlank(message = "resourceId can't be empty")
    private String resourceId;

    /**
     * 扩展字段
     */
    private String ext;

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
}
