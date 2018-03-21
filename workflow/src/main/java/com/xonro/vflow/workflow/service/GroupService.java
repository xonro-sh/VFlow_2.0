package com.xonro.vflow.workflow.service;

import org.activiti.engine.identity.Group;

import java.util.List;

/**
 * 用户组服务
 * @author louie
 * @date created in 2018-3-20 19:00
 */
public interface GroupService {

    /**
     * 获取所有分组
     * @return
     */
    List<Group> findAll();

    /**
     * 获取用户所在分组
     * @param userId
     * @return
     */
    List<Group> findUserGroup(String userId);

    /**
     * 保存分组信息,已存在则更新，不存在则创建
     * @param groupId
     * @param groupName
     * @param groupType
     * @return
     */
    Group saveGroup(String groupId,String groupName,String groupType);
}
