package com.xonro.vflow.workflow.web;

import com.alibaba.fastjson.JSON;
import com.xonro.vflow.bases.bean.NodeResponse;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.workflow.bean.PortalMenu;
import com.xonro.vflow.workflow.service.PortalMenuService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Alex
 * @date 2018/4/26 14:08
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/portal")
public class PortalMenuController {
    private final PortalMenuService portalMenuService;

    @Autowired
    public PortalMenuController(PortalMenuService portalMenuService) {
        this.portalMenuService = portalMenuService;
    }

    /**
     * 新建门户菜单
     * @param portalMenu 门户菜单对象
     * @return 创建成功的门户菜单
     */
    @RequestMapping(value = "/menu_create",method = RequestMethod.POST)
    public PortalMenu createDepartment(@Valid @RequestBody PortalMenu portalMenu){
        return portalMenuService.createPortalMenu(portalMenu);
    }

    /**
     * 更新门户菜单
     * @param portalMenu 门户菜单对象
     * @return 更新后的部门
     */
    @RequestMapping(value = "/menu_update",method = RequestMethod.POST)
    public PortalMenu updatePortalMenu(@Valid @RequestBody PortalMenu portalMenu) throws VFlowException{
        return portalMenuService.updatePortalMenu(portalMenu);
    }

    /**
     * 获取子菜单
     * @param parentId 父菜单id
     * @return 子菜单列表
     * @throws VFlowException
     */
    @RequestMapping(value = "/menu_subs")
    public List<PortalMenu> getSubPortalMenus(@NotBlank(message = "menu_parentId can not be empty") String parentId) throws VFlowException {
        return portalMenuService.getSubPortalMenus(parentId);
    }

    /**
     * 获取子菜单（layui）
     * @param parentId 父菜单id
     * @return 子菜单列表
     */
    @RequestMapping(value = "/menu_subs_table")
    public String getSubPortalMenusByTable(@NotBlank(message = "menu_parentId can not be empty") String parentId){
        return JSON.toJSONString(portalMenuService.getSubPortalMenusByTable(parentId));
    }

    /**
     * 删除菜单
     * @param id 部门id
     * @return
     */
    @RequestMapping(value = "/menu_delete")
    public PortalMenu delete(@NotBlank(message = "menu_id can not be empty") String id) throws VFlowException {
        return portalMenuService.delete(id);
    }
    /**
     * 获取父菜单
     * @param subId 子菜单id
     * @return 父菜单
     * @throws VFlowException
     */
    @RequestMapping(value = "/menu_parent")
    public PortalMenu getParentPortalMenu(@NotBlank(message = "menu_id can not be empty") String subId) throws VFlowException {
        return portalMenuService.getParentPortalMenu(subId);
    }

    /**
     * 获取所有一级门户菜单
     * @param tenantId 租户ID
     * @return 一级门户菜单列表
     */
    @RequestMapping(value = "/menu_firsts")
    public List<PortalMenu> firstPortalMenu(@NotBlank(message = "tenantId can not be empty") String tenantId){
        return portalMenuService.firstPortalMenu(tenantId);
    }

    /**
     * 获取所有一级门户菜单(layui)
     * @param tenantId 租赁id
     * @return 一级门户菜单列表
     */
    @RequestMapping(value = "/menu_firsts_table")
    public String rootDepartmentByTable(@NotBlank(message = "tenantId can not be empty") String tenantId){
        return JSON.toJSONString(portalMenuService.firstPortalMenuByTable(tenantId));
    }


    @RequestMapping(value = "/menu_tree")
    public List<NodeResponse> getPortalMenusByTree(@NotBlank(message = "tenantId can not be empty") String tenantId) throws VFlowException{
        return portalMenuService.getPortalMenusByTree(tenantId);
    }
}
