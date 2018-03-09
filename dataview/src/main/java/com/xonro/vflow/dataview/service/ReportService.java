package com.xonro.vflow.dataview.service;

import com.xonro.vflow.bases.bean.BaseResponse;
import com.xonro.vflow.bases.bean.TableResponse;
import com.xonro.vflow.dataview.bean.ReportTheme;

/**
 * @author Alex
 * @date 2018/2/11
 */
public interface ReportService {
    /**
     * 获取所有主题的配置
     * @return 结果
     */
    TableResponse getReportTheme();

    /**
     * 新增或者更新主题配置
     * @param reportTheme 数据对象
     * @return 结果
     */
    BaseResponse updateReportTheme(ReportTheme reportTheme);
}
