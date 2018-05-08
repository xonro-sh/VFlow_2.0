package com.xonro.vflow.dataview.service;

import com.xonro.vflow.bases.bean.BaseResponse;
import com.xonro.vflow.bases.bean.TableResponse;
import com.xonro.vflow.dataview.bean.DataView;
import com.xonro.vflow.dataview.bean.DataViewTheme;

/**
 * @author Alex
 * @date 2018/2/11
 */
public interface DataViewService {
    /**
     * 获取所有主题的配置
     * @return 结果
     */
    TableResponse getAllDataViewTheme();

    /**
     * 新增或者更新主题配置
     * @param dataViewTheme 数据对象
     * @return 结果
     */
    BaseResponse updateDataViewTheme(DataViewTheme dataViewTheme);

    /**
     * 获取主题的配置
     * @return 结果
     */
    DataViewTheme getDataViewThemeFromCache();

    DataViewTheme updateDataViewThemeCache();

    /**
     * 获取数据源sql的字段名
     * @return
     */
    BaseResponse getTableColumns(String sql);

    /**
     * 根据ID获取dataView配置
     * @param id id
     * @return
     */
    BaseResponse getDataView(String id);

    /**
     * 保存报表
     * @param dataView 报表
     * @return
     */
    BaseResponse saveDataView(DataView dataView);

    /**
     * 获取报表用的数据
     * @param id id
     * @param xAxis 横坐标
     * @param series 系列
     * @return
     */
    BaseResponse getDataSet(String id, String xAxis, String series);

    /**
     * 获取表格类型的报表数据
     * @param id id
     * @return
     */
    TableResponse getDataViewByTable(String id);

    /**
     * 删除报表
     * @param dataView
     * @return
     */
    BaseResponse delDataView(DataView dataView);

    /**
     * 获取矩形树图数据
     * @param id id
     * @param param json数据
     * @return
     */
    BaseResponse getTreeMapDataView(String id, String param);

    /**
     * 获取数据表格配置
     * @param id
     * @return
     */
    BaseResponse getDataGridConf(String id);

    /**
     * 获取数据表格数据
     * @param id
     * @return
     */
    TableResponse getDataGridDataSet(String id,Integer page, Integer rows, String data);

}
