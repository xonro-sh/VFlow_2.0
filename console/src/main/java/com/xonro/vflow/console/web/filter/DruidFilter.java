package com.xonro.vflow.console.web.filter;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * 阿里巴巴Druid监控拦截配置
 * @author louie
 * @date created in 2018-3-22 22:52
 */
@WebFilter(
        filterName = "druidFilter",urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
        }
)
public class DruidFilter extends WebStatFilter{
}
