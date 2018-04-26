package com.xonro.vflow.workflow.service.impl;

import com.xonro.vflow.bases.bean.NodeResponse;
import com.xonro.vflow.bases.bean.TableResponse;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.workflow.bean.PermissionView;
import com.xonro.vflow.workflow.bean.PortalMenu;
import com.xonro.vflow.workflow.dao.PortalMenuRepository;
import com.xonro.vflow.workflow.enums.OrgEnum;
import com.xonro.vflow.workflow.service.PermissionService;
import com.xonro.vflow.workflow.service.PortalMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex
 * @date 2018/4/26 11:56
 */
@Slf4j
@Service
public class PortalMenuServiceImpl implements PortalMenuService {
    private final PortalMenuRepository portalMenuRepository;
    private PermissionService permissionService;

    @Autowired
    public PortalMenuServiceImpl(PortalMenuRepository portalMenuRepository) {
        this.portalMenuRepository = portalMenuRepository;
    }

    @Override
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public PortalMenu createPortalMenu(PortalMenu portalMenu) {
        return portalMenuRepository.save(portalMenu);
    }

    @Override
    public PortalMenu updatePortalMenu(PortalMenu portalMenu) throws VFlowException {
        try{
            if (portalMenu == null){
                throw new VFlowException("error","portalMenu not exist");
            }
            return portalMenuRepository.save(portalMenu);
        }catch (VFlowException e){
            log.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public PortalMenu getPortalMenuById(String id) {
        return portalMenuRepository.findById(id);
    }

    @Override
    public PortalMenu delete(String id) throws VFlowException {
        PortalMenu portalMenu = portalMenuRepository.findById(id);
        if (portalMenu != null) {
            if (getSubPortalMenus(portalMenu.getId()).size() !=0){
                throw new VFlowException("error","portalMenu has sub portalMenu,do not delete");
            } else {
                List<PermissionView> permissions = permissionService.getByResourceId(id);
                //删除权限组
                if (permissions.size() != 0){
                    for (PermissionView permissionView:permissions){
                        permissionService.delete(permissionView.getId());
                    }
                }
                //删除菜单
                portalMenuRepository.delete(portalMenu);
            }
        }
        return portalMenu;
    }

    @Override
    public List<NodeResponse> getPortalMenusByTree(String tenantId) throws VFlowException {
        //一级菜单
        List<PortalMenu> portalMenus= firstPortalMenu(tenantId);
        //一级菜单节点
        List<NodeResponse> allNodes = new ArrayList<>();
        //一级菜单节点
        List<NodeResponse> firstNodes = new ArrayList<>();
        for (PortalMenu portalMenu: portalMenus){
            //下级菜单
            List<NodeResponse> lowerNodes = new ArrayList<>();
            firstNodes.add(new NodeResponse(portalMenu.getCnName(), portalMenu.getId(), getSubPortalMenusByTree(lowerNodes, portalMenu.getId())));
        }
        NodeResponse firstNode = new NodeResponse(OrgEnum.PORTAL_MENU_ROOT_NAME.getValue(), OrgEnum.PORTAL_MENU_ROOT_ID.getValue(), true, firstNodes);
        allNodes.add(firstNode);
        return allNodes;
    }

    private List<NodeResponse> getSubPortalMenusByTree(List<NodeResponse> nodeResponses,String parentId) throws VFlowException{
        List<PortalMenu> portalMenus = getSubPortalMenus(parentId);

        if (portalMenus.size() != 0){
            for (PortalMenu portalMenu:portalMenus){
                if (getSubPortalMenus(portalMenu.getId()).size()!=0){
                    List<NodeResponse> nodeResponses1 = new ArrayList<>();
                    nodeResponses.add(new NodeResponse(portalMenu.getCnName(), portalMenu.getId(), getSubPortalMenusByTree(nodeResponses1, portalMenu.getId())));
                } else {
                    nodeResponses.add(new NodeResponse(portalMenu.getCnName(), portalMenu.getId()));
                }
            }
        }
        return nodeResponses;
    }

    @Override
    public List<PortalMenu> getSubPortalMenus(String parentId) throws VFlowException {
        PortalMenu parentPortalMenu = portalMenuRepository.findById(parentId);
        try {
            if (parentPortalMenu == null){
                throw new VFlowException("error","portalMenu not exist,parentId:"+parentId);
            }
            return portalMenuRepository.findByParentIdOrderByOrderIndexAsc(parentId);
        } catch (VFlowException e) {
            log.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public TableResponse getSubPortalMenusByTable(String parentId) {
        List<PortalMenu> portalMenus;
        TableResponse tableResponse = new TableResponse(0,"",0, "");
        try {
            portalMenus = getSubPortalMenus(parentId);
            tableResponse.setCount((long) portalMenus.size());
            tableResponse.setData(portalMenus);
        } catch (VFlowException e) {
            log.error(e.getMessage(),e);
            tableResponse.setCode(1);
            tableResponse.setMsg(e.getErrorCode());
        }
        return tableResponse;
    }

    @Override
    public PortalMenu getParentPortalMenu(String subId) throws VFlowException {
        PortalMenu subPortalMenu = portalMenuRepository.findById(subId);
        try {
            if (subPortalMenu == null){
                throw new VFlowException("error","portalMenu not exist,subId:"+subId);
            }
            return portalMenuRepository.findById(subPortalMenu.getParentId());
        } catch (VFlowException e) {
            log.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public List<PortalMenu> firstPortalMenu(String tenantId) {
        return portalMenuRepository.findByTenantIdAndParentId(tenantId, OrgEnum.PORTAL_MENU_ROOT_ID.getValue());
    }

    @Override
    public TableResponse firstPortalMenuByTable(String tenantId) {
        List<PortalMenu> portalMenus = portalMenuRepository.findByTenantIdAndParentId(tenantId, OrgEnum.PORTAL_MENU_ROOT_ID.getValue());
        return new TableResponse(0,"",portalMenus.size(), portalMenus);
    }
}
