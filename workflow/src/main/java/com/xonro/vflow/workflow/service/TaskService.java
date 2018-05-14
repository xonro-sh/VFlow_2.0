package com.xonro.vflow.workflow.service;

import org.activiti.engine.history.HistoricTaskInstance;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author Alex
 * @date 2018/5/11 15:41
 */
public interface TaskService {
    /**
     * 查询历史任务
     * @param assignee 办理人
     * @param requestMap 参数
     * @return 历史任务
     * @throws ParseException
     */
    List<HistoricTaskInstance> historyTaskInfo(String assignee, Map<String, String> requestMap) throws ParseException;
}
