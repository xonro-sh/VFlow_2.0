package com.xonro.vflow.dataview.service.impl;

import com.xonro.vflow.bases.bean.BaseResponse;
import com.xonro.vflow.bases.bean.TableResponse;
import com.xonro.vflow.dataview.bean.ReportTheme;
import com.xonro.vflow.dataview.dao.ReportThemeRepository;
import com.xonro.vflow.dataview.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alex
 * @date 2018/2/11
 */
@Service
public class ReportServiceImpl implements ReportService {
    private final ReportThemeRepository reportThemeRepository;

    @Autowired
    public ReportServiceImpl(ReportThemeRepository reportThemeRepository) {
        this.reportThemeRepository = reportThemeRepository;
    }


    @Override
    public TableResponse getReportTheme() {
        List<ReportTheme> reportThemes = reportThemeRepository.findAll();
        return new TableResponse(0, "", reportThemes.size(), reportThemes);
    }

    @Override
    public BaseResponse updateReportTheme(ReportTheme reportTheme) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setOk(true);
        try {
            reportThemeRepository.save(reportTheme);
        } catch (Exception e) {
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }
}
