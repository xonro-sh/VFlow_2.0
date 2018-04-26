package com.xonro.vflow.workflow.dao;

import com.xonro.vflow.workflow.bean.PortalMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alex
 * @date 2018/4/25 15:27
 */
public interface PortalMenuRepository extends JpaRepository<PortalMenu, Long>{
    /**
     * 根据parentId获取所有子菜单
     * @param parentId 菜单父id
     * @return 结果
     */
    List<PortalMenu> findByParentIdOrderByOrderIndexAsc(String parentId);

    /**
     * 根据id获取某一菜单记录
     * @param id 菜单id
     * @return 结果
     */
    PortalMenu findById(String id);

    /**
     * 获取租户下的一级菜单
     * @param tenantId 租户id
     * @param parentId
     * @return
     */
    List<PortalMenu> findByTenantIdAndParentId(String tenantId, String parentId);
}
