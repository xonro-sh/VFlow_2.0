package com.xonro.vflow.portal.service.impl;

import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.portal.bean.PortalResponse;
import com.xonro.vflow.portal.service.PortalService;
import com.xonro.vflow.workflow.bean.Department;
import com.xonro.vflow.workflow.bean.PermissionView;
import com.xonro.vflow.workflow.bean.PortalMenu;
import com.xonro.vflow.workflow.bean.Role;
import com.xonro.vflow.workflow.enums.OrgEnum;
import com.xonro.vflow.workflow.service.DepartmentService;
import com.xonro.vflow.workflow.service.PermissionService;
import com.xonro.vflow.workflow.service.PortalMenuService;
import com.xonro.vflow.workflow.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Alex
 * @date 2018/4/27 16:38
 */
@Service
public class PortalServiceImpl implements PortalService{
    private final UserService userService;
    private final PermissionService permissionService;
    private final PortalMenuService portalMenuService;
    private final DepartmentService departmentService;

    @Autowired
    public PortalServiceImpl(UserService userService, PermissionService permissionService, PortalMenuService portalMenuService, DepartmentService departmentService) {
        this.userService = userService;
        this.permissionService = permissionService;
        this.portalMenuService = portalMenuService;
        this.departmentService = departmentService;
    }


    @Override
    public List<PortalResponse> getPortalMenuByUserId(String userId) throws VFlowException {
        //用户部门
        Department department = userService.userDepartment(userId);
        //用户角色
        Role role = userService.getUserRole(userId);
        //组id
        String departmentGroupId = department == null? null : department.getGroupId();
        String roleGroupId = role == null? null : role.getGroupId();
        List<PermissionView> departmentPermissionViews = null;
        List<PermissionView> rolePermissionViews = null;
        if (StringUtils.isNotEmpty(departmentGroupId)){
            departmentPermissionViews = permissionService.getPermissionViewByGroupId(departmentGroupId);
            //增加其父部门权限
            getParentPermissionViews(department, departmentPermissionViews);
        }
        if (StringUtils.isNotEmpty(roleGroupId)){
            rolePermissionViews = permissionService.getPermissionViewByGroupId(roleGroupId);
        }
        //资源列表
        Set<String> resourceIdSet = new HashSet<>();
        //权限组去重
        assert departmentPermissionViews != null:"user do not have permission";
        for (PermissionView permissionView: departmentPermissionViews){
            resourceIdSet.add(permissionView.getResourceId());
        }
        assert rolePermissionViews != null;
        for (PermissionView permissionView: rolePermissionViews){
            resourceIdSet.add(permissionView.getResourceId());
        }
        List<PortalResponse> firstPortalMenu = new ArrayList<>();
        List<PortalMenu> portalMenus = new ArrayList<>();
        for (String resourceId:resourceIdSet){
            PortalMenu portalMenu = portalMenuService.getPortalMenuById(resourceId);
            portalMenus.add(portalMenu);

        }
        for (PortalMenu portalMenu: portalMenus){
            //如果为根菜单
            if (portalMenu.getParentId().equals(OrgEnum.PORTAL_MENU_ROOT_ID.getValue())){
                //下级菜单
                List<PortalResponse> lowerPortalMenu = new ArrayList<>();
                firstPortalMenu.add(new PortalResponse(portalMenu.getCnName(), portalMenu.getId(), portalMenu.getUrl(), portalMenu.getMenuIcon(), getSubPortalMenu(lowerPortalMenu, portalMenus, portalMenu.getId())));
            }
        }
        return firstPortalMenu;
    }

    private void getParentPermissionViews(Department department, List<PermissionView> departmentPermissionViews) throws VFlowException {
        Department department1 = departmentService.getParentDepartment(department.getId());
        String parentDepartmentGroupId = department1 == null? null : department1.getGroupId();
        if (StringUtils.isNotEmpty(parentDepartmentGroupId)){
            departmentPermissionViews.addAll(permissionService.getPermissionViewByGroupId(parentDepartmentGroupId));
        }
    }

    private List<PortalResponse> getSubPortalMenu(List<PortalResponse> lowerPortalMenu, List<PortalMenu> portalMenus, String parentId) {
        for (PortalMenu portalMenu: portalMenus){
            if (portalMenu.getParentId().equals(parentId)){
                //下级菜单
                List<PortalResponse> lowerPortalMenu1 = new ArrayList<>();
                lowerPortalMenu.add(new PortalResponse(portalMenu.getCnName(), portalMenu.getId(), portalMenu.getUrl(),portalMenu.getMenuIcon(), getSubPortalMenu(lowerPortalMenu1, portalMenus, portalMenu.getId())));
            }
        }
        return lowerPortalMenu;
    }
}
