package com.xonro.vflow.dataview.web;

import com.alibaba.fastjson.JSON;
import com.xonro.vflow.bases.bean.BaseResponse;
import com.xonro.vflow.dataview.bean.ReportTheme;
import com.xonro.vflow.dataview.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alex
 * @date 2018/2/11
 */
@EnableAutoConfiguration
@RestController
@RequestMapping(value = "/report")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * 获取所有主题的配置
     * @return 结果
     */
    @RequestMapping(value = "/getReportTheme")
    public String getReportTheme(){
        return JSON.toJSONString(reportService.getReportTheme());
    }

    /**
     * 新增或者更新主题配置
     * @param data 数据(json)
     * @return 结果
     */
    @RequestMapping(value = "/updateReportTheme")
    public BaseResponse updateReportTheme(String data){
        return reportService.updateReportTheme(JSON.parseObject(data, ReportTheme.class));
    }
}
