package com.xonro.vflow.workflow.service;

import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.workflow.bean.Department;
import org.activiti.engine.identity.User;

import java.util.List;

/**
 * 部门服务接口
 * @author louie
 * @date created in 2018-3-22 15:54
 */
public interface DepartmentService {

    /**
     * 新增部门
     * @param department
     * @return 创建成功的部门
     */
    Department createDepartment(Department department);

    /**
     * 更新部门信息
     * @param departmentId 部门id
     * @param departmentName 部门名称
     * @param parentId 父部门id
     * @param orderIndex 排序
     * @return 更新后的部门
     * @throws VFlowException
     */
    Department updateDepartment(String departmentId,String departmentName,String parentId,Integer orderIndex) throws VFlowException;

    /**
     * 获取部门
     * @param departmentId
     * @return
     */
    Department getDepartmentById(String departmentId);

    /**
     * 获取子部门
     * @param parentDepartmentId 父部门id
     * @return 子部门列表
     * @throws VFlowException
     */
    List<Department> getSubDepartments(String parentDepartmentId) throws VFlowException;

    /**
     * 获取父部门
     * @param subDepartmentId 子部门id
     * @return 父部门
     * @throws VFlowException
     */
    Department getParentDepartment(String subDepartmentId) throws VFlowException;

    /**
     * 获取所有部门
     * @return
     */
    List<Department> findAll();

    /**
     * 获取所有根部门
     * @return 跟部门列表
     */
    List<Department> rootDepartment();

    /**
     * 获取部门所属用户
     * @param departmentId
     * @return
     * @throws VFlowException
     */
    List<User> departmentUsers(String departmentId) throws VFlowException;

}
