package com.xonro.vflow.workflow.dao;

import com.xonro.vflow.workflow.bean.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 部门仓库
 * @author louie
 * @date created in 2018-4-17 10:54
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    /**
     * 根据部门id查询
     * @param departmentId
     * @return
     */
    Department findById(String departmentId);

    /**
     * 获取父部门
     * @param parentId
     * @return
     */
    List<Department> findByParentId(String parentId);

    /**
     * 无父部门的部门列表
     * @return
     */
    List<Department> findByParentIdIsNull();

    /**
     * 获取分组对应的部门
     * @param groupId
     * @return
     */
    Department findByGroupId(String groupId);
}
