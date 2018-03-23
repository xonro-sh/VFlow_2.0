package com.xonro.vflow.workflow.dao;

import com.xonro.vflow.workflow.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 角色数据仓库
 * @author louie
 * @date created in 2018-3-22 18:51
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
}
