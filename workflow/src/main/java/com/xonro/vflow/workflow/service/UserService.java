package com.xonro.vflow.workflow.service;

import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;

import java.util.List;

/**
 * @author louie
 * @date created in 2018-3-14 22:00
 */
public interface UserService {

    /**
     * 保存用户信息
     * @param userId y用户id
     * @param firstName 姓
     * @param lastName 名
     * @param email 邮箱
     * @param password 用户密码
     * @return
     */
    User saveUser(String userId,String firstName,String lastName,String email,String password);

    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return
     */
    User findUserById(String userId);


    /**
     * 删除用户信息
     * @param userId
     */
    void deleteUserById(String userId);

    /**
     * 获取所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 分页获取用户列表
     * @param offset 查询偏移量
     * @param limit 查询数量
     * @return 用户列表
     */
    List<User> listPage(Integer offset,Integer limit);

    /**
     * 设置用户图片
     * @param userId 用户id
     * @param pictureData 图片字节数组
     * @param pictureName 图片名称
     * @return 保存的图片信息
     */
    Picture setUserPicture(String userId, byte[] pictureData, String pictureName);
}
