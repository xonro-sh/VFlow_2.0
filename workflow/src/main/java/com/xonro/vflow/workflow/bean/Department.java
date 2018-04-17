package com.xonro.vflow.workflow.bean;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 部门
 * @author louie
 * @date created in 2018-3-22 15:23
 */
@Entity
@Table(name = "b_xr_vflow_department")
public class Department implements Serializable{
    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @NotBlank(message = "departmentName can't be empty")
    private String name;

    private String parentId;
    private Integer orderIndex;
    private String groupId;

    @NotBlank(message = "tenantId can't be empty")
    private String tenantId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
