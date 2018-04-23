package com.xonro.vflow.workflow.service.impl;

import com.xonro.vflow.workflow.service.WorkflowService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author louie
 * @date created in 2018-4-20 11:54
 */
public class WorkflowServiceImpl implements WorkflowService {
    @Autowired
    private TaskService taskService;

    @Autowired
    IdentityService identityService;

    @Override
    public List<Task> userTask(String userId) {

        return null;
    }
}
