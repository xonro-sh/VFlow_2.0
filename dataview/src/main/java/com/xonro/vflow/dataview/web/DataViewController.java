package com.xonro.vflow.dataview.web;

import com.alibaba.fastjson.JSON;
import com.xonro.vflow.bases.bean.BaseResponse;
import com.xonro.vflow.dataview.bean.DataViewTheme;
import com.xonro.vflow.dataview.service.DataViewService;
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
@RequestMapping(value = "/dataview")
public class DataViewController {
    private final DataViewService dataViewService;

    @Autowired
    public DataViewController(DataViewService dataViewService) {
        this.dataViewService = dataViewService;
    }

    /**
     * 获取所有主题的配置
     * @return 结果
     */
    @RequestMapping(value = "/getAllDataViewTheme")
    public String getAllDataViewTheme(){
        return JSON.toJSONString(dataViewService.getAllDataViewTheme());
    }

    /**
     * 新增或者更新主题配置
     * @param data 数据(json)
     * @return 结果
     */
    @RequestMapping(value = "/updateDataViewTheme")
    public BaseResponse updateDataViewTheme(String data){
        return dataViewService.updateDataViewTheme(JSON.parseObject(data, DataViewTheme.class));
    }

    /**
     * 获取主题配置
     * @return 结果
     */
    @RequestMapping(value = "/getDataViewTheme")
    public BaseResponse getDataViewTheme(){
        return dataViewService.getDataViewTheme();
    }
}
