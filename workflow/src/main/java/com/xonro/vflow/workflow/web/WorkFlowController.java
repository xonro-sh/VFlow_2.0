package com.xonro.vflow.workflow.web;

import com.xonro.vflow.bases.bean.BaseResponse;
import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author louie
 * @date created in 2018-4-9 16:08
 */
@RestController

@RequestMapping(value = "/workflow")
public class WorkFlowController {

    @Autowired
    private RepositoryService repositoryService ;

    @RequestMapping(value = "/count")
    public BaseResponse workflowCount(){
        return new BaseResponse(
                true,"success","",repositoryService.createProcessDefinitionQuery().count()
        );
    }

}
