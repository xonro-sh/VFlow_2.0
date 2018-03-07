package com.xonro.vflow.wechat.bean.menu;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author Alex
 * @date 2018/1/16
 */
public class MenuResult {
    private String errCode;
    private String errMsg;
    /**
     * 在设置个性化菜单时返回
     */
    private String menuId;

    @JSONField(name = "errcode")
    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    @JSONField(name = "errmsg")
    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @JSONField(name = "menuid")
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
