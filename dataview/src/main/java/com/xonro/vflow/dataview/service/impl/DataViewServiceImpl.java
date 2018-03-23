package com.xonro.vflow.dataview.service.impl;

import com.alibaba.fastjson.JSON;
import com.xonro.vflow.bases.bean.BaseResponse;
import com.xonro.vflow.bases.bean.TableResponse;
import com.xonro.vflow.dataview.bean.DataView;
import com.xonro.vflow.dataview.bean.DataViewTheme;
import com.xonro.vflow.dataview.dao.DataViewRepository;
import com.xonro.vflow.dataview.dao.DataViewThemeRepository;
import com.xonro.vflow.dataview.helper.DataViewHelper;
import com.xonro.vflow.dataview.service.DataViewService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.tiles3.SpringBeanPreparerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alex
 * @date 2018/2/11
 */
@Service
public class DataViewServiceImpl implements DataViewService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final DataViewThemeRepository dataViewThemeRepository;
    @PersistenceContext
    private EntityManager em;
    private final DataViewRepository dataViewRepository;
    @Autowired
    public DataViewServiceImpl(DataViewThemeRepository dataViewThemeRepository, DataViewRepository dataViewRepository) {
        this.dataViewThemeRepository = dataViewThemeRepository;
        this.dataViewRepository = dataViewRepository;
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

    @Override
    public BaseResponse getTableColumns(String sql) {
        BaseResponse baseResponse = new BaseResponse(){{
            setOk(true);
            setData("");
            setMsg("");
        }};
        try {
        if (StringUtils.isNotEmpty(sql) && sql.startsWith("select")){
                Query query = em.createNativeQuery(sql);
                query.unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                if (query.getResultList().size() !=0 ){
                    List list = query.getResultList();
                    Map map = (Map) list.get(0);
                    List columnList = new ArrayList();
                    columnList.addAll(map.keySet());
                    baseResponse.setData(columnList);
                } else {
                    baseResponse.setOk(false);
                    baseResponse.setMsg("当前数据源无数据");
                }
            } else {
                baseResponse.setOk(false);
                baseResponse.setMsg("请输入正确的查询语句");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }

        return baseResponse;
    }

    @Override
    public BaseResponse getDataView(String id) {
        BaseResponse baseResponse = new BaseResponse(){{
            setOk(true);
            setData("");
            setMsg("");
        }};
        try {
            DataView dataView = dataViewRepository.findById(id);
            baseResponse.setData(JSON.toJSONString(dataView));
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }

        return baseResponse;
    }

    @Override
    public BaseResponse saveDataView(DataView dataView) {
        BaseResponse baseResponse = new BaseResponse(){{
            setOk(true);
            setData("");
            setMsg("");
        }};
        try {
            if (dataView.getId() != null){
                DataView dataView1 = dataViewRepository.findById(dataView.getId());
                if (dataView.getReportAttr()!=null){
                    dataView1.setReportAttr(dataView.getReportAttr());
                }
                if (dataView.getQueryStat() != null) {
                    dataView1.setQueryStat(dataView.getQueryStat());
                    dataView1.setColumnProp(dataView.getColumnProp());
                    dataView1.setDataSource(dataView.getDataSource());
                }
                dataViewRepository.save(dataView1);
            } else {
                dataViewRepository.save(dataView);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }

    @Override
    public BaseResponse getDataSet(String id, String xAxis, String series) {
        BaseResponse baseResponse = new BaseResponse(){{
            setOk(true);
            setData("");
            setMsg("");
        }};
        try {
            DataView dataView = dataViewRepository.findById(id);
            String sql = dataView.getQueryStat();
            String tableName = DataViewHelper.findTableNameFromSql(sql);
            String newSql = " select "+series+ ","+xAxis+" from "+ tableName + " where 1=1 ";
            Query query = em.createNativeQuery(newSql);
            query.unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            if (query.getResultList().size() !=0 ){
                List list = query.getResultList();
                baseResponse.setData(JSON.toJSONString(list));
            } else {
                baseResponse.setOk(false);
                baseResponse.setMsg("当前数据源无数据");
            }
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }

    @Override
    public TableResponse getDataViewByTable(String id) {
        TableResponse tableResponse = new TableResponse(){{
           setCode(0);
           setMsg("");
        }};
        try {
            List<DataView> dataViews = dataViewRepository.findAll();
            tableResponse.setCount((long) dataViews.size());
            tableResponse.setData(dataViews);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            tableResponse.setCode(1);
            tableResponse.setMsg(e.getMessage());
        }
        return tableResponse;
    }

    @Override
    public BaseResponse delDataView(DataView dataView) {
        BaseResponse baseResponse = new BaseResponse(){{
            setOk(true);
            setData("");
            setMsg("");
        }};
        try {
            dataViewRepository.delete(dataView);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }
}
