package com.xonro.vflow.workflow.bean;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 新建用户
 * @author louie
 * @date created in 2018-4-16 15:51
 */
public class CreateUser implements Serializable{
    /**
     * 用户id
     */
    @NotBlank(message = "user id can not be empty")
    private String userId;

    /**
     * 姓
     */
    @NotBlank(message = "firstName can not be empty")
    private String firstName;

    /**
     * 名
     */
    private String lastName;

    /**
     * 邮箱
     */
    @Email
    private String email;

    /**
     * 用户密码
     */
    @NotBlank(message = "password can not be empty")
    private String password;

    /**
     * 租赁id
     */
    @NotBlank(message = "tenantId can not be empty")
    private String tenantId;

    /**
     * 部门id
     */
    @NotBlank(message = "departmentId can not be empty")
    private String departmentId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}
