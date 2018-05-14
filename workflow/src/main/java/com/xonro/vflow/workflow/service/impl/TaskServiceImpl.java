package com.xonro.vflow.workflow.service.impl;

import com.xonro.vflow.workflow.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author Alex
 * @date 2018/5/11 15:41
 */
@Slf4j
@Service
public class TaskServiceImpl implements TaskService{
    private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    @Override
    public List<HistoricTaskInstance> historyTaskInfo(String assignee,Map<String, String> requestMap) throws ParseException {
        try {
            HistoricTaskInstanceQuery query = processEngine.getHistoryService().createHistoricTaskInstanceQuery().taskAssignee(assignee).finished();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (requestMap.containsKey("createdBefore")){
                query.taskCreatedBefore(sdf.parse(requestMap.get("createdBefore")));
            }
            if (requestMap.containsKey("createdAfter")){
                query.taskCreatedAfter(sdf.parse(requestMap.get("createdAfter")));
            }
            if (requestMap.containsKey("nameLike")){
                query.taskNameLike(requestMap.get("nameLike"));
            }
            return query.list();
        } catch (ParseException e) {
            log.error(e.getMessage(),e);
            throw e;
        }

    }
}
