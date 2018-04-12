package com.xonro.vflow.console.enums;

/**
 * console枚举
 *
 * @author Alex
 * @date 2018/3/8 13:38
 */
public enum ConsoleEnums {
    /**
     * 菜单常量
     */
    MENU_UP("up","菜单向上换位置"),
    MENU_DOWN("down","菜单向下换位置"),
        ;

    ConsoleEnums(String value, String desc){
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
