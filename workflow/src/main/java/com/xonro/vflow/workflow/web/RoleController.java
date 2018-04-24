package com.xonro.vflow.workflow.web;

import com.alibaba.fastjson.JSON;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.workflow.bean.Role;
import com.xonro.vflow.workflow.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.identity.User;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色请求控制器
 *
 * @author louie
 * @date created in 2018-4-19 21:41
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 创建角色
     * @param roleName
     * @param tenantId
     * @return 创建后的角色对象
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Role createRole(@NotBlank(message = "roleName can not be empty") String roleName,
                           @NotBlank(message = "tenantId can not be empty") String tenantId) {
        return roleService.createRole(roleName, tenantId);
    }

    /**
     * 删除角色
     * @param roleId 角色id
     * @return 被删除的角色对象，角色不存在时返回null
     */
    @RequestMapping(value = "/delete")
    public Role deleteRole(@NotBlank(message = "roleId can not be empty") String roleId) {
        return roleService.deleteRole(roleId);
    }

    /**
     * 更新角色
     * @param roleId
     * @param roleName
     * @return
     * @throws VFlowException
     */
    @RequestMapping(value = "/update")
    public Role updateRole(@NotBlank(message = "roleId can not be empty") String roleId,
                           @NotBlank(message = "roleName can not be empty") String roleName) throws VFlowException {
        return roleService.updateRole(roleId, roleName);
    }

    /**
     * 获取租户所有角色
     * @param tenantId
     * @return
     */
    @RequestMapping(value = "/all")
    public List<Role> getAll(String tenantId) {
        return roleService.getAll(tenantId);
    }

    /**
     * 获取租户所有角色(layui)
     * @param tenantId 租户id
     * @return
     */
    @RequestMapping(value = "/all_table")
    public String getAllByTable(String tenantId){
        return JSON.toJSONString(roleService.getAllByTable(tenantId));
    }

    /**
     * 获取角色对象
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/get")
    public Role getById(@NotBlank(message = "roleId can not be empty") String roleId) {
        return roleService.getRoleById(roleId);
    }

    /**
     * 获取角色用户
     * @param roleId
     * @return
     * @throws VFlowException
     */
    @RequestMapping(value = "/users")
    public List<User> roleUser(@NotBlank(message = "roleId can not be empty") String roleId) throws VFlowException {
        return roleService.getRoleUser(roleId);
    }

}
