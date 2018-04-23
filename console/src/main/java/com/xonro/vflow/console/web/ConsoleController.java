package com.xonro.vflow.console.web;

import com.alibaba.fastjson.JSON;
import com.xonro.vflow.bases.bean.BaseResponse;
import com.xonro.vflow.console.bean.Menu;
import com.xonro.vflow.console.service.LoginService;
import com.xonro.vflow.console.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Alex
 * @date 2018/1/23
 */
@RestController
@RequestMapping(value = "/console")
public class ConsoleController {
    private final MenuService menuService;

    private final LoginService loginService;

    @Autowired
    public ConsoleController(MenuService menuService, LoginService loginService) {
        this.menuService = menuService;
        this.loginService = loginService;
    }

    /**
     * 增加menu
     * @param data menu json数据
     * @return 结果
     */
    @RequestMapping(value = "/menu_add",method = RequestMethod.POST)
    public BaseResponse addMenu(String data){
        return menuService.addMenu(JSON.parseObject(data, Menu.class));
    }

    /**
     * 获取menu
     * @return 结果 json格式
     */
    @RequestMapping(value = "/get_menu_page",method = RequestMethod.GET)
    public String getMenuByPage(Integer page, Integer limit){
        return JSON.toJSONString(menuService.getMenuByPage(page, limit));
    }

    /**
     * 获取menu
     * @return 结果
     */
    @RequestMapping(value = "/get_menu")
    public BaseResponse getMenu(){
        return menuService.getMenu();
    }

    /**
     * 删除menu
     * @param id menu的id
     * @return 结果
     */
    @RequestMapping(value = "/menu_del")
    public BaseResponse delMenu(String id){
        return menuService.delMenu(id);
    }

    /**
     * 根据id获取menu
     * @param id menu的id
     * @return 结果
     */
    @RequestMapping(value = "/get_menu_by_id")
    public BaseResponse getMenuById(String id){
        return menuService.getMenuById(id);
    }

    /**
     * 获取树形菜单结构
     * @return 结果
     */
    @RequestMapping(value = "/get_menu_tree")
    public BaseResponse getMenuByTree(){
        return menuService.getMenuByTree();
    }

    /**
     * 根据父级代码获取其下所有此单
     * @param pNo 父级代码
     * @return 结果
     */
    @RequestMapping(value = "/get_menu_by_pno/{pNo}",method = RequestMethod.GET)
    public String getMenuByPNo(@PathVariable String pNo){
        return JSON.toJSONString(menuService.getMenuByPNo(pNo));
    }

    /**
     * 更换索引
     * @param itemNo 代码
     * @param type 类型 up or down
     * @return 结果
     */
    @RequestMapping(value = "/change_menu_index")
    BaseResponse changeMenuIndex(String itemNo, String type){
        return menuService.changeMenuIndex(itemNo, type);
    }

    /**
     *  后台登陆
     * @param account 账号
     * @param password 密码
     * @return 结果
     */
    @RequestMapping(value = "/login")
    BaseResponse login(String account, String password, HttpSession session){
        BaseResponse response = loginService.getTenantInfoFromCache(account, password);
        if (response.isOk()){
            session.setAttribute(session.getId(),account);
        }
        return response;
    }
}
