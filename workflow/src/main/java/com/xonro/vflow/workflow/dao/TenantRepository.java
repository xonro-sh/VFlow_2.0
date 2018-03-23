package com.xonro.vflow.workflow.dao;

import com.xonro.vflow.workflow.bean.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author louie
 * @date created in 2018-3-22 15:56
 */
@Repository
public interface TenantRepository extends JpaRepository<Tenant,Long>{
    /**
     * 根据id获取租户
     * @param id
     * @return
     */
    Tenant findById(String id);
}
