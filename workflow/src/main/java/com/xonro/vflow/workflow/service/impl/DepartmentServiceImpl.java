package com.xonro.vflow.workflow.service.impl;

import com.google.common.base.Strings;
import com.xonro.vflow.bases.bean.NodeResponse;
import com.xonro.vflow.bases.bean.TableResponse;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.workflow.bean.Department;
import com.xonro.vflow.workflow.dao.DepartmentRepository;
import com.xonro.vflow.workflow.enums.OrgEnum;
import com.xonro.vflow.workflow.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.soap.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author louie
 * @date created in 2018-4-17 10:53
 */
@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService{

    @Resource
    private DepartmentRepository repository;

    @Resource
    private IdentityService identityService;

    @Override
    @CachePut(value = "department",key = "#department.id",unless = "#result eq null ")
    public Department createDepartment(Department department) {
        Group group = identityService.newGroup(UUID.randomUUID().toString().replaceAll("-",""));
        group.setName(department.getName());
        group.setType(OrgEnum.GROUP_TYPE_DEPARTMENT.getValue());
        identityService.saveGroup(group);

        department.setGroupId(group.getId());
        return repository.save(department);
    }

    @Override
    @CachePut(value = "department",key = "#departmentId",unless = "#result eq null ")
    public Department updateDepartment(String departmentId, String departmentName, String parentId, Integer orderIndex) throws VFlowException {
        Department department = repository.findById(departmentId);

        try {
            if (department == null){
                throw new VFlowException("error","department not exist,departmentId:"+departmentId);
            }
            if (!Strings.isNullOrEmpty(departmentName)){
                department.setName(departmentName);
            }
            if (!Strings.isNullOrEmpty(parentId)){
                department.setParentId(parentId);
            }
            if (orderIndex != null){
                department.setOrderIndex(orderIndex);
            }
            return repository.save(department);
        } catch (VFlowException e) {
            log.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    @Cacheable(value = "department",key = "#departmentId",unless = "#result eq null ")
    public Department getDepartmentById(String departmentId) {
        return repository.findById(departmentId);
    }

    @Override
    public Department delete(String departmentId) throws VFlowException{
        Department department = repository.findById(departmentId);
        if (department != null) {
            if (getSubDepartments(department.getId()).size() !=0){
                throw new VFlowException("error","department has sub department,do not delete");
            } else {
                String groupId = department.getGroupId();
                identityService.deleteGroup(groupId);
                repository.delete(department);
            }
        }
        return department;
    }

    @Override
    public List<NodeResponse> getDepartmentsByTree(String tenantId) throws VFlowException {
        //根部门
        List<Department> departments= rootDepartment(tenantId);
        //根节点
        List<NodeResponse> allNodes = new ArrayList<>();
        for (Department department: departments){
            //一级菜单
            List<NodeResponse> firstNodes = new ArrayList<>();
            allNodes.add(new NodeResponse(department.getName(), department.getId(), getSubDepartmentsByTree(firstNodes, department.getId())));
        }
        return allNodes;
    }

    public List<NodeResponse> getSubDepartmentsByTree(List<NodeResponse> nodeResponses,String parentDepartmentId) throws VFlowException{
        List<Department> subDep = getSubDepartments(parentDepartmentId);

        if (subDep.size() != 0){
            for (Department department:subDep){
                if (getSubDepartments(department.getId()).size()!=0){
                    List<NodeResponse> nodeResponses1 = new ArrayList<>();
                    nodeResponses.add(new NodeResponse(department.getName(), department.getId(), getSubDepartmentsByTree(nodeResponses1, department.getId())));
                } else {
                    nodeResponses.add(new NodeResponse(department.getName(), department.getId()));
                }
            }
        }
        return nodeResponses;
    }
    @Override
    public List<Department> getSubDepartments(String parentDepartmentId) throws VFlowException {
        Department parentDepartment = repository.findById(parentDepartmentId);
        try {
            if (parentDepartment == null){
                throw new VFlowException("error","department not exist,departmentId:"+parentDepartmentId);
            }
            return repository.findByParentId(parentDepartmentId);
        } catch (VFlowException e) {
            log.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public TableResponse getSubDepartmentsByTable(String parentDepartmentId) {
        List<Department> departments = null;
        TableResponse tableResponse = new TableResponse(0,"",0, "");
        try {
            departments = getSubDepartments(parentDepartmentId);
            tableResponse.setCount((long) departments.size());
            tableResponse.setData(departments);
        } catch (VFlowException e) {
            log.error(e.getMessage(),e);
            tableResponse.setCode(1);
            tableResponse.setMsg(e.getErrorCode());
        }
        return tableResponse;
    }

    @Override
    public Department getParentDepartment(String subDepartmentId) throws VFlowException {
        Department subDepartment = repository.findById(subDepartmentId);
        try {
            if (subDepartment == null){
                throw new VFlowException("error","department not exist,departmentId:"+subDepartmentId);
            }
            return repository.findById(subDepartment.getParentId());
        } catch (VFlowException e) {
            log.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public List<Department> findAll(String tenantId) {
        return repository.findByTenantId(tenantId);
    }

    @Override
    public List<Department> rootDepartment(String tenantId) {
        return repository.findByTenantIdAndParentIdIsNull(tenantId);
    }

    @Override
    public TableResponse rootDepartmentByTable(String tenantId) {
        List<Department> departments = repository.findByTenantIdAndParentIdIsNull(tenantId);
        return new TableResponse(0,"",departments.size(), departments);
    }

    @Override
    public List<User> departmentUsers(String departmentId) throws VFlowException {
        Department department = repository.findById(departmentId);
        try {
            if (department == null){
                throw  new VFlowException("error","department not exist,departmentId:"+departmentId);
            }
            return identityService.createUserQuery().memberOfGroup(department.getGroupId()).list();
        } catch (VFlowException e) {
            log.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public TableResponse departmentUsersByTable(String departmentId) {
        TableResponse tableResponse = new TableResponse(0,"",0, "");
        try {
            List<User> users = departmentUsers(departmentId);
            tableResponse.setCount((long) users.size());
            tableResponse.setData(users);
        } catch (VFlowException e) {
            log.error(e.getMessage(),e);
            tableResponse.setCode(1);
            tableResponse.setMsg(e.getErrorCode());
        }
        return tableResponse;
    }

}
