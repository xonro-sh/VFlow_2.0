package com.xonro.vflow.workflow.web;

import com.alibaba.fastjson.JSON;
import com.xonro.vflow.bases.bean.JsTreeResponse;
import com.xonro.vflow.bases.bean.NodeResponse;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.workflow.bean.Department;
import com.xonro.vflow.workflow.bean.UserInfo;
import com.xonro.vflow.workflow.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.identity.User;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 部门服务控制器
 * @author louie
 * @date created in 2018-4-17 9:26
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 创建部门
     * @param department
     * @return 成功返回创建后的部门信息，失败则返回失败信息
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public Department createDepartment(@Valid @RequestBody Department department){
        return departmentService.createDepartment(department);
    }

    /**
     * 更新部门
     * @param departmentId
     * @param departmentName
     * @param parentId
     * @param orderIndex
     * @return 成功返回更新后的部门信息，失败则返回失败信息
     * @throws VFlowException
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Department updateDepartment(@NotBlank(message = "departmentId can not be empty") String departmentId,
                                       String departmentName, String parentId, Integer orderIndex) throws VFlowException {
        return departmentService.updateDepartment(departmentId,departmentName,parentId,orderIndex);
    }

    /**
     * 获取子部门
     * @param departmentId
     * @return 子部门列表
     * @throws VFlowException
     */
    @RequestMapping(value = "/subs")
    public List<Department> subDepartments(@NotBlank(message = "departmentId can not be empty") String departmentId) throws VFlowException {
        return departmentService.getSubDepartments(departmentId);
    }

    @RequestMapping(value = "/subs_table")
    public String subDepartmentsByTable(@NotBlank(message = "departmentId can not be empty") String departmentId){
        return JSON.toJSONString(departmentService.getSubDepartmentsByTable(departmentId));
    }

    /**
     * 删除部门
     * @param departmentId 部门id
     * @return
     * @throws VFlowException
     */
    @RequestMapping(value = "/delete")
    public Department delete(@NotBlank(message = "departmentId can not be empty") String departmentId) throws VFlowException {
        return departmentService.delete(departmentId);
    }
    /**
     * 获取父部门
     * @param departmentId
     * @return 成功返回父部门，失败返回失败信息
     * @throws VFlowException
     */
    @RequestMapping(value = "/parent")
    public Department parentDepartment(@NotBlank(message = "departmentId can not be empty") String departmentId) throws VFlowException {
        return departmentService.getParentDepartment(departmentId);
    }

    /**
     * 获取所有根部门
     * @param tenantId 租赁id
     * @return
     */
    @RequestMapping(value = "/roots")
    public List<Department> rootDepartments(@NotBlank(message = "tenantId can not be empty") String tenantId){
        return departmentService.rootDepartment(tenantId);
    }

    /**
     * 获取所有根部门(layui)
     * @param tenantId 租赁id
     * @return
     */
    @RequestMapping(value = "/roots_table")
    public String rootDepartmentByTable(String tenantId){
        return JSON.toJSONString(departmentService.rootDepartmentByTable(tenantId));
    }

    /**
     * 获取所有部门
     * @param tenantId
     * @return
     */
    @RequestMapping(value = "/all")
    public List<Department> all(@NotBlank(message = "tenantId can not be empty") String tenantId){
        return departmentService.findAll(tenantId);
    }

    /**
     * 部门用户列表
     * @param departmentId
     * @return 成功则返回用户列表，失败返回失败信息
     * @throws VFlowException
     */
    @RequestMapping(value = "/users")
    public List<User> users(@NotBlank(message = "departmentId can not be empty") String departmentId) throws VFlowException {

        return departmentService.departmentUsers(departmentId);
    }

    /**
     * 部门用户信息列表
     * @param departmentId
     * @return 成功则返回用户列表，失败返回失败信息
     * @throws VFlowException
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "/userinfos")
    public List<UserInfo> departmentUserInfos(@NotBlank(message = "departmentId can not be empty") String departmentId) throws VFlowException, IllegalAccessException{
        return departmentService.departmentUserInfos(departmentId);
    }

    /**
     * 部门用户列表 (layui)
     * @param departmentId
     * @return 成功则返回用户列表，失败返回失败信息
     */
    @RequestMapping(value = "/users_table")
    public String usersByTable(String departmentId){
        return JSON.toJSONString(departmentService.departmentUsersByTable(departmentId));
    }

    @RequestMapping(value = "/tree")
    public List<NodeResponse> getDepartmentsByTree(@NotBlank(message = "tenantId can not be empty") String tenantId) throws VFlowException {
        return departmentService.getDepartmentsByTree(tenantId);
    }

    @RequestMapping(value = "/js_tree")
    public List<JsTreeResponse> getDepartmentsByJsTree(@NotBlank(message = "tenantId can not be empty") String tenantId) throws VFlowException {
        return departmentService.getDepartmentsByJsTree(tenantId);
    }

}
