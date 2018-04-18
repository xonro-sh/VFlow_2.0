package com.xonro.vflow.workflow.service.impl;

import com.google.common.base.Strings;
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

}
