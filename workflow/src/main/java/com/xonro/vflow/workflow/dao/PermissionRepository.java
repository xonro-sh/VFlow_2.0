package com.xonro.vflow.workflow.dao;

import com.xonro.vflow.workflow.bean.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alex
 * @date 2018/4/25 13:11
 */
public interface PermissionRepository extends JpaRepository<Permission, Long>{
    /**
     * 根据ID获取权限
     * @param id 权限组ID
     * @return 结果
     */
    Permission findById(String id);

    /**
     * 获取某个资源的权限组
     * @param resourceId 资源id
     * @return
     */
    List<Permission> findByResourceId(String resourceId);
}
