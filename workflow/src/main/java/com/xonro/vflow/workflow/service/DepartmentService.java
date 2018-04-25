package com.xonro.vflow.workflow.service;

import com.xonro.vflow.bases.bean.NodeResponse;
import com.xonro.vflow.bases.bean.TableResponse;
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
     * 删除部门
     * @param departmentId 部门id
     * @throws VFlowException
     * @return
     */
    Department delete(String departmentId) throws VFlowException;

    /**
     * 获取固定结构的部门菜单
     * @param tenantId 租户ID
     * @return list菜单
     * @throws VFlowException
     */
    List<NodeResponse> getDepartmentsByTree(String tenantId) throws VFlowException;

    /**
     * 获取子部门
     * @param parentDepartmentId 父部门id
     * @return 子部门列表
     * @throws VFlowException
     */
    List<Department> getSubDepartments(String parentDepartmentId) throws VFlowException;


    /**
     * 获取子部门（layui）
     * @param parentDepartmentId 父部门id
     * @return 子部门列表
     */
    TableResponse getSubDepartmentsByTable(String parentDepartmentId);

    /**
     * 获取父部门
     * @param subDepartmentId 子部门id
     * @return 父部门
     * @throws VFlowException
     */
    Department getParentDepartment(String subDepartmentId) throws VFlowException;

    /**
     * 获取所有部门
     * @param tenantId 租赁id
     * @return
     */
    List<Department> findAll(String tenantId);

    /**
     * 获取所有根部门
     * @param tenantId
     * @return 跟部门列表
     */
    List<Department> rootDepartment(String tenantId);

    /**
     * 获取所有根部门（layui）
     * @param tenantId
     * @return
     */
    TableResponse rootDepartmentByTable(String tenantId);

    /**
     * 获取部门所属用户
     * @param departmentId
     * @return
     * @throws VFlowException
     */
    List<User> departmentUsers(String departmentId) throws VFlowException;

    /**
     * 获取部门所属用户（layui）
     * @param departmentId
     * @return
     */
    TableResponse departmentUsersByTable(String departmentId);

}
