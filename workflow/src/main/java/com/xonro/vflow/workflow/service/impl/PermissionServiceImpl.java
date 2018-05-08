package com.xonro.vflow.workflow.service.impl;

import com.xonro.vflow.bases.bean.TableResponse;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.workflow.bean.Permission;
import com.xonro.vflow.workflow.bean.PermissionView;
import com.xonro.vflow.workflow.bean.PortalMenu;
import com.xonro.vflow.workflow.dao.PermissionRepository;
import com.xonro.vflow.workflow.dao.PermissionViewRepository;
import com.xonro.vflow.workflow.service.PermissionService;
import com.xonro.vflow.workflow.service.PortalMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex
 * @date 2018/4/25 11:28
 */
@Service
public class PermissionServiceImpl implements PermissionService{
    private final PermissionViewRepository permissionViewRepository;
    private final PermissionRepository permissionRepository;
    private final PortalMenuService portalMenuService;
    @PostConstruct
    public void init() {
        portalMenuService.setPermissionService(this);
    }
    @Autowired
    public PermissionServiceImpl(PermissionViewRepository permissionViewRepository, PermissionRepository permissionRepository, PortalMenuService portalMenuService) {
        this.permissionViewRepository = permissionViewRepository;
        this.permissionRepository = permissionRepository;
        this.portalMenuService = portalMenuService;
    }

    @Override
    public List<Permission> create(Permission permission) throws VFlowException {
        List<Permission> permissions = new ArrayList<>();
        permissions.add(permission);
        createParentMenuPermission(permission,permissions);
        return permissionRepository.save(permissions);
    }

    /**
     * 创建父菜单权限（保持父菜单权限）
     * @param permission
     * @param permissions
     * @throws VFlowException
     */
    private void createParentMenuPermission(Permission permission, List<Permission> permissions) throws VFlowException {
        if (StringUtils.isNotEmpty(permission.getResourceId())){
            PortalMenu portalMenu = portalMenuService.getParentPortalMenu(permission.getResourceId());
            if (portalMenu != null){
                List<PermissionView> permissionViews = permissionViewRepository.findByResourceId(portalMenu.getId());
                int count = 0;
                for (PermissionView permissionView : permissionViews){
                    //如果已有该权限
                    if (permissionView.getGroupId().equals(permission.getGroupId())){
                        count++;
                    }
                }
                Permission permission1 = new Permission();
                permission1.setGroupId(permission.getGroupId());
                permission1.setResourceId(portalMenu.getId());
                permission1.setResourceType(permission.getResourceType());
                if (count==0){
                    permissions.add(permission1);
                }
                createParentMenuPermission(permission1, permissions);
            }
        }
    }


    @Override
    public List<PermissionView> getByResourceId(String resourceId) {
        return permissionViewRepository.findByResourceId(resourceId);
    }

    @Override
    public TableResponse getPermissionByTable(String resourceId) {
        List<PermissionView> permissionViews = permissionViewRepository.findByResourceId(resourceId);
        return new TableResponse(0,"",permissionViews.size(), permissionViews);
    }

    @Override
    public Permission delete(String permissionId){
        Permission permission = permissionRepository.findById(permissionId);
        permissionRepository.delete(permission);
        return permission;
    }

    @Override
    public List<PermissionView> getPermissionViewByGroupId(String groupId) {
        return permissionViewRepository.findByGroupId(groupId);
    }
}
