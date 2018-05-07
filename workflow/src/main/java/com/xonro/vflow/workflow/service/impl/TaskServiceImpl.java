package com.xonro.vflow.workflow.service.impl;

import com.xonro.vflow.workflow.service.TaskService;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.IdentityService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author louie
 * @date created in 2018-4-20 11:54
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private org.activiti.engine.TaskService taskService;


    @Autowired
    IdentityService identityService;

    @Override
    public List<Task> userTask(String userId) {

        return null;
    }

    public List<Process> userProcess(String userId) {

        return null;
    }
}
