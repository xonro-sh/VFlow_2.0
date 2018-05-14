package com.xonro.vflow.workflow.web;

import com.xonro.vflow.workflow.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.history.HistoricTaskInstance;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author Alex
 * @date 2018/5/11 15:57
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/task")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/history",method = RequestMethod.POST)
    public List<HistoricTaskInstance> historyTaskInfo(@NotBlank(message = "assignee can not be empty") String assignee,@RequestParam Map<String, String> requestMap) throws ParseException {
        return taskService.historyTaskInfo(assignee, requestMap);
    }
}
