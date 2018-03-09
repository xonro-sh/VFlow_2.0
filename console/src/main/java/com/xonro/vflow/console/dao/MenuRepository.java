package com.xonro.vflow.console.dao;

import com.xonro.vflow.console.bean.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alex
 * @date 2018/1/23
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    /**
     * 分页查找所有
     * @param pageable 分页对象
     * @return 分页后的菜单对象
     */
    @Override
    Page<Menu> findAll(Pageable pageable);

    /**
     * 根据ID查找信息
     * @param id id
     * @return 菜单对象
     */
    Menu findById(String id);

    /**
     * 根据父级id查找信息
     * @param pNo 父节点
     * @return 菜单对象列表
     */
    List<Menu> findByPNoOrderByItemNoDesc(String pNo);

    /**
     * 根据菜单代码查找菜单对象
     * @param itemNo 菜单代码
     * @return 菜单对象
     */
    Menu findByItemNo(String itemNo);

}
