package com.xonro.vflow.workflow.bean;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 门户菜单配置实体
 * @author Alex
 * @date 2018/4/25 14:47
 */
@Entity
@Table(name="b_xr_portal_menu")
public class PortalMenu implements Serializable{
    /**
     * 代码
     */
    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    /**
     *  父级代码
     */
    @NotBlank(message = "parentId can't be empty")
    private String parentId;
    /**
     * 中文名称
     */
    @NotBlank(message = "cnName can't be empty")
    private String cnName;
    /**
     * 英文名称
     */
    private String enName;
    /**
     * 有效性
     */
    private String isActive;
    /**
     * 菜单链接
     */
    private String url;
    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 顺序
     */
    private Integer orderIndex;

    /**
     * 租户ID
     */
    @NotBlank(message = "tenantId can't be empty")
    private String tenantId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
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
