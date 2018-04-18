package com.xonro.vflow.dataview.bean;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

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
     * 数据表格基本参数
     */
    private String datagridBasicAttr;

    /**
     * 数据表格排序参数
     */
    private String datagridSortAttr;

    /**
     * 类型 大类
     */
    private String type;

    /**
     * 图表参数
     */
    private String reportAttr;

    /**
     * 查询条件
     */
    private String queryCondition;

    /**
     * 扩展字段
     */
    private String extText;

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

    @Column(name = "datagrid_basic_attr")
    public String getDatagridBasicAttr() {
        return datagridBasicAttr;
    }

    public void setDatagridBasicAttr(String datagridBasicAttr) {
        this.datagridBasicAttr = datagridBasicAttr;
    }

    @Column(name = "datagrid_sort_attr")
    public String getDatagridSortAttr() {
        return datagridSortAttr;
    }

    public void setDatagridSortAttr(String datagridSortAttr) {
        this.datagridSortAttr = datagridSortAttr;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "report_attr")
    public String getReportAttr() {
        return reportAttr;
    }

    public void setReportAttr(String reportAttr) {
        this.reportAttr = reportAttr;
    }

    @Column(name = "query_condition")
    @Length(max = 3000)
    public String getQueryCondition() {
        return queryCondition;
    }

    @Column(name = "query_stat")
    public void setQueryCondition(String queryCondition) {
        this.queryCondition = queryCondition;
    }

    @Column(name = "ext_text")
    public String getExtText() {
        return extText;
    }

    public void setExtText(String extText) {
        this.extText = extText;
    }
}
