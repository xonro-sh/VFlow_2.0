package com.xonro.vflow.workflow.service;

import com.xonro.vflow.bases.bean.NodeResponse;
import com.xonro.vflow.bases.bean.TableResponse;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.workflow.bean.PortalMenu;

import java.util.List;

/**
 * @author Alex
 * @date 2018/4/26 11:55
 */
public interface PortalMenuService {
    void setPermissionService(PermissionService permissionService);
    /**
     * 新建门户菜单
     * @param portalMenu 门户菜单对象
     * @return 创建成功的门户菜单
     */
    PortalMenu createPortalMenu(PortalMenu portalMenu);

    /**
     * 更新门户菜单
     * @param portalMenu 门户菜单对象
     * @throws VFlowException
     * @return 更新后的部门
     */
    PortalMenu updatePortalMenu(PortalMenu portalMenu) throws VFlowException;

    /**
     * 获取门户菜单
     * @param id 菜单id
     * @return 获取到的菜单对象
     */
    PortalMenu getPortalMenuById(String id);

    /**
     * 删除菜单
     * @param id 部门id
     * @throws VFlowException
     * @return
     */
    PortalMenu delete(String id) throws VFlowException;

    /**
     * 获取固定结构的portal菜单
     * @param tenantId 租户
     * @throws VFlowException
     * @return list菜单
     */
    List<NodeResponse> getPortalMenusByTree(String tenantId) throws VFlowException;

    /**
     * 获取子菜单
     * @param parentId 父菜单id
     * @return 子菜单列表
     * @throws VFlowException
     */
    List<PortalMenu> getSubPortalMenus(String parentId) throws VFlowException;


    /**
     * 获取子菜单（layui）
     * @param parentId 父菜单id
     * @return 子菜单列表
     */
    TableResponse getSubPortalMenusByTable(String parentId);

    /**
     * 获取父菜单
     * @param subId 子菜单id
     * @return 父菜单
     * @throws VFlowException
     */
    PortalMenu getParentPortalMenu(String subId) throws VFlowException;

    /**
     * 获取所有一级门户菜单
     * @param tenantId 租户ID
     * @return 一级门户菜单列表
     */
    List<PortalMenu> firstPortalMenu(String tenantId);

    /**
     * 获取所有一级门户菜单 (layui）
     * @param tenantId 租户ID
     * @return 一级门户菜单列表
     */
    TableResponse firstPortalMenuByTable(String tenantId);
}
