package com.xonro.vflow.portal.enums;

/**
 * @author Alex
 * @date 2018/4/27 16:39
 */
public enum PortalEnums {
    ;
    private PortalEnums(String value,String desc){
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
