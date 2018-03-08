package com.xonro.vflow.wechat.service;

import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.wechat.bean.menu.Button;
import com.xonro.vflow.wechat.bean.menu.MenuResult;

import java.io.IOException;

/**
 * 菜单管理相关接口
 * @author Alex
 * @date 2018/1/16
 */
public interface WechatMenuService {
    /**
     * 创建菜单
     * @param button 菜单按钮
     * @return 结果
     * @throws VFlowException 异常
     * @throws IOException 异常
     */
    MenuResult menuCreate(Button button) throws VFlowException, IOException;

    /**
     * 查询菜单
     * @return 菜单json串
     * @throws IOException 异常
     * @throws VFlowException 异常
     */
    String menuGet() throws IOException, VFlowException;

    /**
     * 删除菜单 （在个性化菜单时，调用此接口会删除默认菜单及全部个性化菜单）
     * @return 删除结果
     * @throws IOException 异常
     * @throws VFlowException 异常
     */
    MenuResult menuDel() throws IOException, VFlowException;

    /**
     * 创建个性化菜单
     * @param button 菜单按钮
     * @return 创建结果 若成功返回menuid
     * @throws IOException 异常
     * @throws VFlowException 异常
     */
    MenuResult menuAddConditional(Button button) throws IOException, VFlowException;

    /**
     * 删除个性化菜单
     * @param menuId 菜单id
     * @return 删除结果
     * @throws IOException 异常
     * @throws VFlowException 异常
     */
    MenuResult menuDelConditional(String menuId) throws IOException, VFlowException;

    /**
     * 测试个性化菜单匹配结果
     * @param userId 粉丝的openID或者其微信号
     * @return 菜单json串
     * @throws IOException 异常
     * @throws VFlowException 异常
     */
    String menuTryMatch(String userId) throws IOException, VFlowException;

    /**
     * 获取自定义菜单配置
     * @return 菜单json串
     * @throws VFlowException 异常
     * @throws IOException 异常
     */
    String getCurrentSelfMenu() throws VFlowException, IOException;
}
