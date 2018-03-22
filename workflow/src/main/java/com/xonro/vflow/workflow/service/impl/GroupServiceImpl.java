package com.xonro.vflow.workflow.service.impl;

import com.xonro.vflow.workflow.service.GroupService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author louie
 * @date created in 2018-3-21 18:08
 */
@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private IdentityService identityService;

    @Override
    public List<Group> findAll() {
        return identityService.createGroupQuery().list();
    }

    @Override
    public List<Group> findUserGroup(String userId) {
        return identityService.createGroupQuery().groupMember(userId).list();
    }

    @Override
    public Group saveGroup(String groupId, String groupName, String groupType) {
        Group group = identityService.createGroupQuery().groupId(groupId).singleResult();
        if (group == null){
            group = identityService.newGroup(groupId);
        }

        group.setName(groupName);
        group.setType(groupType);
        identityService.saveGroup(group);
        return group;
    }
}
