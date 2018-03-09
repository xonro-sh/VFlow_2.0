package com.xonro.vflow.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.bases.helper.RequestExecutor;
import com.xonro.vflow.wechat.bean.menu.Button;
import com.xonro.vflow.wechat.bean.menu.MenuResult;
import com.xonro.vflow.wechat.helper.UrlBuilder;
import com.xonro.vflow.wechat.service.WechatMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.TreeMap;

/**
 * @author Alex
 * @date 2018/1/16
 */
@Service
public class WechatMenuServiceImpl implements WechatMenuService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UrlBuilder urlBuilder;

    @Autowired
    public WechatMenuServiceImpl(UrlBuilder urlBuilder) {
        this.urlBuilder = urlBuilder;
    }

    /**
     * 创建菜单
     *
     * @param button 菜单按钮
     * @return 结果
     * @throws VFlowException 异常
     * @throws IOException 异常
     */
    @Override
    public MenuResult menuCreate(Button button) throws VFlowException, IOException {
        MenuResult menuResult;
        try {
            menuResult = new RequestExecutor(urlBuilder.buildMenuCreateUrl())
                    .execute(JSON.toJSONString(button))
                    .getResponseAsObject(MenuResult.class);
            return menuResult;
        } catch (IOException | VFlowException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    /**
     * 查询菜单
     *
     * @return 菜单json串
     * @throws IOException 异常
     * @throws VFlowException 异常
     */
    @Override
    public String menuGet() throws IOException, VFlowException {
        try {
            return new RequestExecutor(urlBuilder.buildMenuGetUrl())
                    .execute()
                    .getResponseAsObject(String.class);
        } catch (IOException | VFlowException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    /**
     * 删除菜单 （在个性化菜单时，调用此接口会删除默认菜单及全部个性化菜单）
     *
     * @return 删除结果
     * @throws IOException 异常
     * @throws VFlowException 异常
     */
    @Override
    public MenuResult menuDel() throws IOException, VFlowException {
        try {
            String result = new RequestExecutor(urlBuilder.buildMenuDelUrl())
                    .execute()
                    .getResponseAsObject(String.class);
            System.err.println("result=="+result);
            return JSON.parseObject(result, MenuResult.class);
        } catch (IOException | VFlowException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    /**
     * 创建个性化菜单
     *
     * @param button 菜单按钮
     * @return 创建结果 若成功返回menuid
     * @throws IOException 异常
     * @throws VFlowException 异常
     */
    @Override
    public MenuResult menuAddConditional(Button button) throws IOException, VFlowException {
        MenuResult menuResult;
        try {
            menuResult = new RequestExecutor(urlBuilder.buildMenuConditionalUrl())
                    .execute(JSON.toJSONString(button))
                    .getResponseAsObject(MenuResult.class);
            return menuResult;
        } catch (IOException | VFlowException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    /**
     * 删除个性化菜单
     *
     * @param menuId 菜单id
     * @return 删除结果
     * @throws IOException 异常
     * @throws VFlowException 异常
     */
    @Override
    public MenuResult menuDelConditional(String menuId) throws IOException, VFlowException {
        //提交的参数
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("menuid", menuId);
        MenuResult menuResult;
        try {
            menuResult = new RequestExecutor(urlBuilder.buildMenuConditionalDelUrl())
                    .execute(JSON.toJSONString(dataParams))
                    .getResponseAsObject(MenuResult.class);
            return menuResult;
        } catch (IOException | VFlowException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    /**
     * 测试个性化菜单匹配结果
     *
     * @param userId 粉丝的openID或者其微信号
     * @return 菜单json串
     * @throws IOException 异常
     * @throws VFlowException 异常
     */
    @Override
    public String menuTryMatch(String userId) throws IOException, VFlowException {
        //提交的参数
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("user_id", userId);
        try {
            return new RequestExecutor(urlBuilder.buildMenuTryMatch())
                    .execute(JSON.toJSONString(dataParams))
                    .getResponseAsObject(String.class);
        } catch (IOException | VFlowException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    /**
     * 获取自定义菜单配置
     *
     * @return 菜单json串
     * @throws VFlowException 异常
     * @throws IOException 异常
     */
    @Override
    public String getCurrentSelfMenu() throws VFlowException, IOException {
        try {
            return new RequestExecutor(urlBuilder.buildGetCurrentSelfMenuUrl())
                    .execute()
                    .getResponseAsObject(String.class);
        } catch (IOException | VFlowException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }


}
