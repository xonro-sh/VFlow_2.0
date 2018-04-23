package com.xonro.vflow.workflow.service;

import org.activiti.engine.task.Task;

import java.util.List;

/**
 * 流程服务
 * @author louie
 * @date created in 2018-4-20 11:49
 */
public interface WorkflowService {

    /**
     * 用户任务
     * @param userId
     * @return
     */
    List<Task> userTask(String userId);

}
