package com.xonro.vflow.dataview.service;

import com.xonro.vflow.bases.bean.BaseResponse;
import com.xonro.vflow.bases.bean.TableResponse;
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
    BaseResponse getDataViewTheme();
}
