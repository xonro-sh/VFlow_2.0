package com.xonro.vflow.workflow.enums;

/**
 * 组织相关枚举
 * @author louie
 * @date created in 2018-4-17 10:35
 */
public enum OrgEnum {
    GROUP_TYPE_DEPARTMENT("department","组类型，部门"),
    GROUP_TYPE_ROLE("role","组类型，角色"),
    /**
     * 权限类型
     */
    RESOURCE_TYPE_PROCESS("process","资源类型，流程"),
    RESOURCE_TYPE_MENU("menu","资源类型，菜单"),
    /**
     * portalMenu常量
     */
    PORTAL_MENU_ROOT_ID("0","门户菜单的根菜单ID"),
    PORTAL_MENU_ROOT_NAME("门户菜单管理","门户菜单的根菜单名称")
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
