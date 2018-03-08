package com.xonro.vflow.console.service.service;

import com.xonro.vflow.bases.bean.BaseResponse;
import com.xonro.vflow.bases.bean.TableResponse;
import com.xonro.vflow.console.bean.Menu;

/**
 * @author Alex
 * @date 2018/1/23
 */
public interface MenuService {
    /**
     * 获取菜单分页结果
     * @param page 第几页
     * @param rows 每页多少条数据
     * @return 分页结果
     */
    TableResponse getMenuByPage(Integer page, Integer rows);

    /**
     * 增加分页
     * @param menu menu
     * @return 结果
     */
    BaseResponse addMenu(Menu menu);

    /**
     * 获取所有菜单
     * @return
     */
    BaseResponse getMenu();

    /**
     * 删除菜单
     * @param id id
     * @return 删除结果
     */
    BaseResponse delMenu(String id);

    /**
     * 根据ID获取菜单
     * @param id id
     * @return 结果
     */
    BaseResponse getMenuById(String id);

    /**
     * 获取树形menu目录
     * @return 结果
     */
    BaseResponse getMenuByTree();

    /**
     * 根据父级代码获取信息
     * @param pNo 父级代码
     * @return 结果
     */
    TableResponse getMenuByPNo(String pNo);

    /**
     * 更换菜单位置
     * @param itemNo 代码
     * @param type 类型 up 向上  down 向下
     * @return 结果
     */
    BaseResponse changeMenuIndex(String itemNo, String type);


}
