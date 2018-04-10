package com.xonro.vflow.workflow.service;

import com.xonro.vflow.workflow.bean.Role;

import java.util.List;

/**
 * 用户角色服务类
 * @author louie
 * @date created in 2018-3-21 18:48
 */
public interface RoleService {

    /**
     * 新建角色
     * @param roleName 角色名称
     * @param tenantId 租赁id
     * @return 角色信息
     */
    Role createRole(String roleName,String tenantId);

    /**
     * 更新角色信息
     * @param roleId 角色id
     * @param roleName 角色名称
     * @return 更新后的角色信息
     */
    Role updateRole(String roleId,String roleName);

    /**
     * 删除角色
     * @param roleId 角色id
     * @return 被删除的角色信息
     */
    Role deleteRole(String roleId);

    /**
     * 获取所有的角色列表
     * @param tenantId 租赁id
     * @return
     */
    List<Role> getAll(String tenantId);

    /**
     * 获取用户的所有角色
     * @param userId
     * @return
     */
    List<Role> getUserRole(String userId);
}
