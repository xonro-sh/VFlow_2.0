package com.xonro.vflow.workflow.service;

import com.xonro.vflow.bases.bean.BaseResponse;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.workflow.bean.CreateUser;
import com.xonro.vflow.workflow.bean.UserInfo;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;

import java.util.List;

/**
 * @author louie
 * @date created in 2018-3-14 22:00
 */
public interface UserService {

    /**
     * 创建用户
     * @param createUser
     * @return
     */
    User createUser(CreateUser createUser);

    /**
     * 设置用户注销、激活状态
     * @param userId
     * @param active
     * @return
     * @throws VFlowException
     */
    BaseResponse setUserActive(String userId,boolean active) throws VFlowException;

    /**
     * 修改用户密码
     * @param userId 用户id
     * @param oldPassword 用户旧密码
     * @param newPassword 新密码
     * @return
     */
    BaseResponse changeUserPassword(String userId,String oldPassword,String newPassword);

    /**
     * 保存用户信息
     * @param userId y用户id
     * @param firstName 姓
     * @param lastName 名
     * @param email 邮箱
     * @return 更新后的用户
     * @throws VFlowException
     */
    User updateUser(String userId,String firstName,String lastName,String email) throws VFlowException;

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
     * 删除用户
     * @param userId
     * @return
     */
    BaseResponse deleteUserById(String userId);

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

    /**
     * 获取用户图片
     * @param userId
     * @return 用户图片信息
     */
    Picture getUserPicture(String userId);

    /**
     * 保存用户信息
     * @param userInfo
     * @return
     */
    UserInfo saveUserInfo(UserInfo userInfo);

    /**
     * 校验用户密码,校验通过返回true，否则返回false
     * @param userId
     * @param password
     * @return
     */
    boolean checkUserPassword(String userId,String password);

    /**
     * 获取用户详细信息
     * @param userId
     * @return
     * @throws VFlowException
     */
    UserInfo getUserInfo(String userId) throws VFlowException;

    /**
     * 获取指定分组内的用户列表
     * @param groupId 分组id
     * @return
     */
    List<User> userInGroup(String groupId);

    /**
     * 获取指定分组内用户列表，分页
     * @param groupId 用户组id
     * @param firstResult 查询起始数量
     * @param limit 查询数量
     * @return
     */
    List<User> userInGroupPage(String groupId,Integer firstResult,Integer limit);
}
