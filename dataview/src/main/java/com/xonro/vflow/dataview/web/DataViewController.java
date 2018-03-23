package com.xonro.vflow.dataview.web;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.regexp.internal.RE;
import com.xonro.vflow.bases.bean.BaseResponse;
import com.xonro.vflow.dataview.bean.DataView;
import com.xonro.vflow.dataview.bean.DataViewTheme;
import com.xonro.vflow.dataview.service.DataViewService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alex
 * @date 2018/2/11
 */
@EnableAutoConfiguration
@RestController
@RequestMapping(value = "/dataview")
public class DataViewController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final DataViewService dataViewService;

    @Autowired
    public DataViewController(DataViewService dataViewService) {
        this.dataViewService = dataViewService;
    }

    /**
     * 获取所有主题的配置
     * @return 结果
     */
    @RequestMapping(value = "/get_all_dataview_theme")
    public String getAllDataViewTheme(){
        return JSON.toJSONString(dataViewService.getAllDataViewTheme());
    }

    /**
     * 新增或者更新主题配置
     * @param data 数据(json)
     * @return 结果
     */
    @RequestMapping(value = "/update_dataview_theme")
    public BaseResponse updateDataViewTheme(String data){
        BaseResponse baseResponse = dataViewService.updateDataViewTheme(JSON.parseObject(data, DataViewTheme.class));
        //更新缓存
        dataViewService.updateDataViewThemeCache();
        return baseResponse;
    }

    /**
     * 获取主题配置
     * @return 结果
     */
    @RequestMapping(value = "/get_dataview_theme")
    public BaseResponse getDataViewTheme(){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setOk(true);
        try {
            //如果有已经生效的主题
            baseResponse.setData(dataViewService.getDataViewThemeFromCache()!=null?dataViewService.getDataViewThemeFromCache().getTheme():"");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }

    @RequestMapping(value = "/get_table_columns")
    BaseResponse getTableColumns(String sql){
        return dataViewService.getTableColumns(sql);
    }

    @RequestMapping(value = "save_dataview")
    BaseResponse saveDataView(String data){
        return dataViewService.saveDataView(JSON.parseObject(data, DataView.class));
    }

    @RequestMapping(value = "get_dataview")
    BaseResponse getDataView(String id){
        return dataViewService.getDataView(id);
    }

    @RequestMapping(value = "get_dataset")
    BaseResponse getDataSet(String id, String xAxis, String series){
        return dataViewService.getDataSet(id, xAxis, series);
    }

    @RequestMapping(value = "get_table_dataview")
    String getDataViewByTable(String id){
        return JSON.toJSONString(dataViewService.getDataViewByTable(id));
    }

    @RequestMapping(value = "del_dataview")
    BaseResponse delDataView(String data){
        return dataViewService.delDataView(JSON.parseObject(data, DataView.class));
    }
}
