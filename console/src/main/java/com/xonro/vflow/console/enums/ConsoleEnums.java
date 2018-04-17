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
    /**
     * 租户登陆，保存信息请求
      */
    URL_TENANT_GET("https://www.xonro.com/tenant/get?","获取租户信息"),
    URL_TENANT_SAVE("https://www.xonro.com/tenant/save","保存租户信息"),
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
