package com.xonro.vflow.dataview.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 报表配置主表
 * @author Alex
 * @date 2018/2/11
 */
@Entity
@Table(name = "b_xr_dataview_main")
public class DataView {
    /**
     * 唯一标识
     */
    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 数据源（可以为其他数据库）
     */
    private String dataSource;

    /**
     * 查询语句
     */
    private String queryStat;

    /**
     * 列属性
     */
    private String columnProp;

    /**
     * 类型 大类
     */
    private String type;

    /**
     * 类型 子类
     */
    private String typeSub;

    /**
     * 图表参数
     */
    private String reportAttr;

    /**
     * 查询条件
     */
    private String queryCondition;

    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "data_source")
    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    @Column(name = "query_stat")
    public String getQueryStat() {
        return queryStat;
    }

    public void setQueryStat(String queryStat) {
        this.queryStat = queryStat;
    }

    @Column(name = "column_prop")
    public String getColumnProp() {
        return columnProp;
    }

    public void setColumnProp(String columnProp) {
        this.columnProp = columnProp;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "type_sub")
    public String getTypeSub() {
        return typeSub;
    }

    public void setTypeSub(String typeSub) {
        this.typeSub = typeSub;
    }

    @Column(name = "report_attr")
    public String getReportAttr() {
        return reportAttr;
    }

    public void setReportAttr(String reportAttr) {
        this.reportAttr = reportAttr;
    }

    @Column(name = "query_condition")
    public String getQueryCondition() {
        return queryCondition;
    }

    public void setQueryCondition(String queryCondition) {
        this.queryCondition = queryCondition;
    }
}
