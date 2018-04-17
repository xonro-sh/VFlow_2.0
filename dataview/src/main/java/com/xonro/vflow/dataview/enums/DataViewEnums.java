package com.xonro.vflow.dataview.enums;

/**
 * 报表枚举
 * @author Alex
 * @date 2018/2/11
 */
public enum DataViewEnums {
    /**
     * 搜索数据类型
     */
    SEARCH_TEXT("text", "文本"),
    SEARCH_NUMBER("number", "数值"),
    SEARCH_DATE("date", "日期"),
    ;

    DataViewEnums(String value, String desc){
        this.value = value;
        this.desc = desc;
    }

    private String value;
    private String desc;

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
