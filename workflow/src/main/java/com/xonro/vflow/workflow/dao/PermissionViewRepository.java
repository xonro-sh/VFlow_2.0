package com.xonro.vflow.workflow.dao;

import com.xonro.vflow.workflow.bean.Permission;
import com.xonro.vflow.workflow.bean.PermissionView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alex
 * @date 2018/4/25 11:25
 */
@Repository
public interface PermissionViewRepository extends JpaRepository<PermissionView, Long>{
    /**
     * 根据资源ID查找权限
     * @param resourceId 资源ID
     * @return 结果
     */
    List<PermissionView> findByResourceId(String resourceId);

    /**
     * 根据组查找权限
     * @param groupId
     * @return
     */
    List<PermissionView> findByGroupId(String groupId);

}
