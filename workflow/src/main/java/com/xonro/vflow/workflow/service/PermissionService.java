package com.xonro.vflow.workflow.service;

import com.xonro.vflow.bases.bean.TableResponse;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.workflow.bean.Permission;
import com.xonro.vflow.workflow.bean.PermissionView;

import java.util.List;

/**
 * @author Alex
 * @date 2018/4/25 11:28
 */
public interface PermissionService {

    /**
     * 新建权限组
     * @param permission 权限组
     * @return 权限组信息
     */
    List<Permission> create(Permission permission) throws VFlowException;
    /**
     * 根据资源ID获取所有权限组
     * @param resourceId 资源ID
     * @return 结果
     */
    List<PermissionView> getByResourceId(String resourceId);

    /**
     * 根据资源ID获取所有权限组（layui）
     * @param resourceId 资源ID
     * @return 结果
     */
    TableResponse getPermissionByTable(String resourceId);

    /**
     * 删除权限
     * @param permissionId 权限组ID
     * @return 被删除的权限信息
     */
    Permission delete(String permissionId) ;

    /**
     * 根据组查找权限
     * @param groupId
     * @return
     */
    List<PermissionView> getPermissionViewByGroupId(String groupId);
}
