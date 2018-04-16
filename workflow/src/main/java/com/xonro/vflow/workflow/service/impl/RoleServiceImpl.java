package com.xonro.vflow.workflow.service.impl;

import com.xonro.vflow.workflow.bean.Role;
import com.xonro.vflow.workflow.dao.RoleRepository;
import com.xonro.vflow.workflow.service.RoleService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author louie
 * @date created in 2018-3-22 18:07
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private IdentityService identityService;

    @Resource
    private RoleRepository roleRepository;

    @Override
    public Role createRole(String roleName, String tenantId) {
        Group group = identityService.newGroup(RandomStringUtils.randomAlphabetic(32).toLowerCase());
        group.setName(roleName);
        group.setType("role");
        identityService.saveGroup(group);

        Role role = new Role();
        role.setGroupId(group.getId());
        role.setName(roleName);
        role.setTenantId(tenantId);

        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(String roleId, String roleName) {
        Role role = roleRepository.findById(roleId);
        Assert.notNull(role,"can not find role where id = "+roleId);

        String groupId = role.getGroupId();
        Group group = identityService.createGroupQuery().groupId(groupId).singleResult();
        group.setName(roleName);
        role.setName(roleName);

        identityService.saveGroup(group);
        roleRepository.save(role);
        return role;
    }

    @Override
    public Role deleteRole(String roleId) {
        Role role = roleRepository.findById(roleId);
        if (role != null){
            String groupId = role.getGroupId();
            identityService.deleteGroup(groupId);
            roleRepository.delete(role);
        }
        return role;
    }

    @Override
    public List<Role> getAll(String tenantId) {
        return roleRepository.findAll();
    }

    @Override
    public List<User> getRoleUser(String roleId) {
        Role role = roleRepository.findById(roleId);
        Assert.notNull(role,"role is not exit,roleId:"+roleId);

        return identityService.createUserQuery().memberOfGroup(role.getGroupId()).list();
    }

}
