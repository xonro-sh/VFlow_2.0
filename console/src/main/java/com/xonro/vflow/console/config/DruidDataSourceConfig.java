package com.xonro.vflow.console.config;

import com.alibaba.druid.support.http.StatViewServlet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里巴巴Druid数据库监控配置
 * @author louie
 * @date created in 2018-3-23 11:19
 */
@Slf4j
@Configuration
public class DruidDataSourceConfig {

    @Bean
    public ServletRegistrationBean druidStatViewServlet(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(
                new StatViewServlet(),"/console/druid/*"
        ){{
            //是否能够重置数据
            addInitParameter("resetEnable","false");
        }};
        return registrationBean;
    }


}
