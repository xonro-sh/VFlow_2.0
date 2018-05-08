package com.xonro.vflow.workflow.config;

import org.activiti.engine.impl.cfg.IdGenerator;
import org.activiti.spring.boot.AbstractProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * @author louie
 * @date created in 2018-3-22 10:58
 */
@Configuration
public class ProcessEngineConfig extends AbstractProcessEngineConfiguration {


    @Bean
    public IdGenerator idGenerator(){
        return new IdGenerator() {
            @Override
            public String getNextId() {
                return UUID.randomUUID().toString().replaceAll("-","");
            }
        };
    }


}
