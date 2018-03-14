package com.xonro.vflow.dataview.service.impl;

import com.xonro.vflow.bases.bean.BaseResponse;
import com.xonro.vflow.bases.bean.TableResponse;
import com.xonro.vflow.dataview.bean.DataViewTheme;
import com.xonro.vflow.dataview.dao.DataViewThemeRepository;
import com.xonro.vflow.dataview.service.DataViewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alex
 * @date 2018/2/11
 */
@Service
public class DataViewServiceImpl implements DataViewService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final DataViewThemeRepository dataViewThemeRepository;

    @Autowired
    public DataViewServiceImpl(DataViewThemeRepository dataViewThemeRepository) {
        this.dataViewThemeRepository = dataViewThemeRepository;
    }


    @Override
    public TableResponse getAllDataViewTheme() {
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        List<DataViewTheme> dataViewThemes = dataViewThemeRepository.findAll(sort);
        return new TableResponse(0, "", dataViewThemes.size(), dataViewThemes);
    }

    @Override
    public BaseResponse updateDataViewTheme(DataViewTheme dataViewTheme) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setOk(true);
        try {
            //如果有已经生效的主题
            if (dataViewTheme.getIsActive()){
                List<DataViewTheme> dataViewThemes = dataViewThemeRepository.findByIsActive(true);
                if (dataViewThemes.size() != 0){
                    for (DataViewTheme dataViewTheme1 : dataViewThemes){
                        dataViewTheme1.setIsActive(false);
                    }
                }
                dataViewThemeRepository.saveAll(dataViewThemes);
            }
            dataViewThemeRepository.save(dataViewTheme);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }

    @Override
    @Cacheable(value = "dataview",key = "#root.targetClass")
    public DataViewTheme getDataViewThemeFromCache(){
        List<DataViewTheme> dataViewThemes = dataViewThemeRepository.findByIsActive(true);
        if (dataViewThemes.size() != 0){
            return dataViewThemes.get(0);
        }
        return null;
    }

    @Override
    @CachePut(value = "dataview",key = "#root.targetClass")
    public DataViewTheme updateDataViewThemeCache(){
        List<DataViewTheme> dataViewThemes = dataViewThemeRepository.findByIsActive(true);
        if (dataViewThemes.size() != 0){
            return dataViewThemes.get(0);
        }
        return null;
    }
}
