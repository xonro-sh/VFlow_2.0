package com.xonro.vflow.workflow.enums;

/**
 * 组织相关枚举
 * @author louie
 * @date created in 2018-4-17 10:35
 */
public enum OrgEnum {
    GROUP_TYPE_DEPARTMENT("department","组类型，部门"),
    GROUP_TYPE_ROLE("role","组类型，角色"),
    ;

    private OrgEnum(String value,String desc){
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
