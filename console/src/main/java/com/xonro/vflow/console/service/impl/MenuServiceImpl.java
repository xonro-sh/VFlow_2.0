package com.xonro.vflow.console.service.impl;

import com.alibaba.fastjson.JSON;
import com.xonro.vflow.bases.bean.BaseResponse;
import com.xonro.vflow.bases.bean.TableResponse;
import com.xonro.vflow.console.bean.Menu;
import com.xonro.vflow.console.bean.NodeResponse;
import com.xonro.vflow.console.dao.MenuRepository;
import com.xonro.vflow.console.enums.ConsoleEnums;
import com.xonro.vflow.console.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex
 * @date 2018/1/23
 */
@Service
public class MenuServiceImpl implements MenuService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MenuRepository menuRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public TableResponse getMenuByPage(Integer page, Integer rows) {
        Page<Menu> list = menuRepository.findAll(new PageRequest(page-1,rows));
        return new TableResponse(0, "", list.getTotalElements(),list.getContent());
    }

    @Override
    public BaseResponse addMenu(Menu menu) {
        BaseResponse baseResponse = new BaseResponse(){{
            setOk(true);
        }};
        try {
            menuRepository.save(menu);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }

    @Override
    public BaseResponse getMenu() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setOk(true);
        baseResponse.setCode("0");
        baseResponse.setMsg("");
        try {
            Sort sort = new Sort(Sort.Direction.ASC, "itemNo");
            baseResponse.setData(JSON.toJSONString(menuRepository.findAll(sort)));
        } catch (Exception e){
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }

    @Override
    public BaseResponse delMenu(String id) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setOk(true);
        try{
            menuRepository.delete(menuRepository.findById(id));

        } catch (Exception e){
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }

    @Override
    public BaseResponse getMenuById(String id) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setOk(true);
        try{
            baseResponse.setData(menuRepository.findById(id));
        } catch (Exception e){
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }

    /**
     * 获取树形menu目录
     *
     * @return 结果
     */
    @Override
    public BaseResponse getMenuByTree() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setOk(true);
        List<Menu> firstMenus = menuRepository.findByPNoOrderByItemNoDesc("0");
        //一级菜单
        List<NodeResponse> allNodes = new ArrayList<>();
        //一级菜单
        List<NodeResponse> firstNodes = new ArrayList<>();
        if (firstMenus.size()!=0){
            for (Menu menu: firstMenus){
                List<Menu> secondMenus = menuRepository.findByPNoOrderByItemNoDesc(menu.getItemNo());
                //二级菜单
                List<NodeResponse> secondNodes = new ArrayList<>();
                if (secondMenus.size()!=0){
                    for (Menu secondMenu: secondMenus){
                        List<Menu> thirdMenus = menuRepository.findByPNoOrderByItemNoDesc(secondMenu.getItemNo());
                        //三级菜单
                        List<NodeResponse> thirdNodes = new ArrayList<>();
                        if (secondMenus.size()!=0){
                            //三级菜单循环
                            for (Menu thirdMenu: thirdMenus){
                                NodeResponse thirdNode = new NodeResponse(thirdMenu.getCnName(), thirdMenu.getItemNo());
                                thirdNodes.add(thirdNode);
                            }
                        }
                        NodeResponse thirdNode = new NodeResponse(secondMenu.getCnName(), secondMenu.getItemNo(), thirdNodes);
                        secondNodes.add(thirdNode);
                    }
                }
                NodeResponse secondNode = new NodeResponse(menu.getCnName(), menu.getItemNo(), secondNodes);
                firstNodes.add(secondNode);
            }
        }
        NodeResponse firstNode = new NodeResponse("菜单管理", "0", true, firstNodes);
        allNodes.add(firstNode);
        baseResponse.setData(JSON.toJSONString(allNodes));
        return baseResponse;
    }

    /**
     * 根据父级代码获取信息
     *
     * @param pNo 父级代码
     * @return 结果
     */
    @Override
    public TableResponse getMenuByPNo(String pNo) {
        List<Menu> menus = menuRepository.findByPNoOrderByItemNoDesc(pNo);
        return new TableResponse(0, "", menus.size(),menus);
    }

    /**
     * 更换菜单位置
     *
     * @param itemNo 代码
     * @param type 类型 up 向上  down 向下
     * @return 结果
     */
    @Override
    public BaseResponse changeMenuIndex(String itemNo, String type) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setOk(true);
        Menu menu = menuRepository.findByItemNo(itemNo);
        int itemNoNew = 0;
        if (type.equals(ConsoleEnums.MENU_UP.getValue())) {
            itemNoNew = Integer.parseInt(itemNo) -1;
        } else if (type.equals(ConsoleEnums.MENU_DOWN.getValue())){
            itemNoNew = Integer.parseInt(itemNo) +1;
        }
        Menu menu1 = menuRepository.findByItemNo(itemNoNew+"");
        if (menu1 == null){
            List<Menu> menuList= menuRepository.findByPNoOrderByItemNoDesc(menu.getpNo());
            if (type.equals(ConsoleEnums.MENU_UP.getValue())) {
                itemNoNew = Integer.parseInt(menuList.get(0).getItemNo()) ;
            } else if (type.equals(ConsoleEnums.MENU_DOWN.getValue())){
                itemNoNew = Integer.parseInt(menuList.get(menuList.size()-1).getItemNo()) ;
            }
        }
        menu1= menuRepository.findByItemNo(itemNoNew+"");
        menu.setItemNo(itemNoNew+"");
        menu1.setItemNo(itemNo);
        List<Menu> menuList = new ArrayList<>();
        menuList.add(menu);
        menuList.add(menu1);
        menuRepository.saveAll(menuList);
        return baseResponse;
    }
}
