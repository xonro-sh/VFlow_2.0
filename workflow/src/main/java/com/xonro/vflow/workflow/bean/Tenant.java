package com.xonro.vflow.workflow.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 租赁用户
 * @author louie
 * @date created in 2018-3-22 14:26
 */
@Entity
@Table(name = "b_xr_vflow_tenant")
public class Tenant implements Serializable{
    /**
     * 租赁id
     */
    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    /**
     * 租赁名称
     */
    private String name;

    /**
     * 租户密码
     */
    private String password;

    /**
     * 租户是否激活
     */
    private boolean active = true;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
