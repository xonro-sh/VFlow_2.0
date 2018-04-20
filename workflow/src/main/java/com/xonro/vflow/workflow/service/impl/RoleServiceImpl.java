package com.xonro.vflow.workflow.service.impl;

import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.workflow.bean.Role;
import com.xonro.vflow.workflow.dao.RoleRepository;
import com.xonro.vflow.workflow.enums.OrgEnum;
import com.xonro.vflow.workflow.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author louie
 * @date created in 2018-3-22 18:07
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private IdentityService identityService;
    @Resource
    private RoleRepository roleRepository;

    @Override
    @CachePut(value = "role", key = "#result.id", unless = "#result eq null ")
    public Role createRole(String roleName, String tenantId) {
        Group group = identityService.newGroup(UUID.randomUUID().toString().replaceAll("-", ""));
        group.setName(roleName);
        group.setType(OrgEnum.GROUP_TYPE_ROLE.getValue());
        identityService.saveGroup(group);

        Role role = new Role();
        role.setGroupId(group.getId());
        role.setName(roleName);
        role.setTenantId(tenantId);
        return roleRepository.save(role);
    }

    @Override
    @CachePut(value = "role", key = "#result.id", unless = "#result eq null ")
    public Role updateRole(String roleId, String roleName) throws VFlowException {
        Role role = roleRepository.findById(roleId);
        try {
            if (role == null) {
                throw new VFlowException("error", "role not exist,roleId:" + roleId);
            }
            String groupId = role.getGroupId();
            Group group = identityService.createGroupQuery().groupId(groupId).singleResult();
            group.setName(roleName);
            role.setName(roleName);

            identityService.saveGroup(group);
            return roleRepository.save(role);
        } catch (VFlowException e) {
            log.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    @CacheEvict(value = "role", key = "#roleId")
    public Role deleteRole(String roleId) {
        Role role = roleRepository.findById(roleId);
        if (role != null) {
            String groupId = role.getGroupId();
            identityService.deleteGroup(groupId);
            roleRepository.delete(role);
        }
        return role;
    }

    @Override
    @Cacheable(value = "role", key = "#roleId", unless = "#result eq null ")
    public Role getRoleById(String roleId) {
        return roleRepository.findById(roleId);
    }

    @Override
    public List<Role> getAll(String tenantId) {
        return roleRepository.findByTenantId(tenantId);
    }

    @Override
    public List<User> getRoleUser(String roleId) throws VFlowException {
        try {
            Role role = roleRepository.findById(roleId);
            if (role == null){
                throw new VFlowException("fail","role not exist,roleId:"+roleId);
            }
            return identityService.createUserQuery().memberOfGroup(role.getGroupId()).list();
        } catch (VFlowException e) {
            log.error(e.getMessage(),e);
            throw e;
        }
    }

}
