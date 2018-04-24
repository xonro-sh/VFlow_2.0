package com.xonro.vflow.workflow.service;

import com.xonro.vflow.bases.bean.TableResponse;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.workflow.bean.Role;
import org.activiti.engine.identity.User;

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
     * @throws VFlowException
     */
    Role updateRole(String roleId,String roleName) throws VFlowException;

    /**
     * 删除角色
     * @param roleId 角色id
     * @return 被删除的角色信息
     */
    Role deleteRole(String roleId);

    /**
     * 获取角色
     * @param roleId
     * @return
     */
    Role getRoleById(String roleId);

    /**
     * 获取所有的角色列表
     * @param tenantId 租赁id
     * @return
     */
    List<Role> getAll(String tenantId);

    /**
     * 获取所有的角色列表
     * @param tenantId 租赁
     * @returnid
     */
    TableResponse getAllByTable(String tenantId);

    /**
     * 获取角色用户
     * @param roleId 角色id
     * @return 拥有角色的用户列表
     * @throws VFlowException
     */
    List<User> getRoleUser(String roleId) throws VFlowException;

    /**
     * 根据分组获取角色
     * @param groupId
     * @return
     */
    Role getRoleByGroupId(String groupId);
}
