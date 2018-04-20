package com.xonro.vflow.workflow.dao;

import com.xonro.vflow.workflow.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色数据仓库
 * @author louie
 * @date created in 2018-3-22 18:51
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
    /**
     * 根据id查询
     * @param id
     * @return
     */
    Role findById(String id);

    /**
     * 根据分组id获取角色
     * @param groupId
     * @return
     */
    Role findByGroupId(String groupId);

    /**
     * 获取租户角色
     * @param tenantId
     * @return
     */
    List<Role> findByTenantId(String tenantId);

}
