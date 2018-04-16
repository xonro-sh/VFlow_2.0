package com.xonro.vflow.workflow.web;

import com.xonro.vflow.bases.bean.BaseResponse;
import com.xonro.vflow.workflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组织相关控制器
 * @author louie
 * @date created in 2018-4-12 16:12
 */
@RestController
@RequestMapping(value = "/org")
public class OrganizationController {

    @Autowired
    private UserService userService;

    /**
     * 新建用户
     * @param userId
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param tenantId
     * @return
     */
    @RequestMapping(value = "/user_create")
    public BaseResponse createUser(String userId, String firstName, String lastName, String email, String password, String tenantId){
        return userService.createUser(userId,firstName,lastName,email,password,tenantId);
    }

    /**
     * 设置用户状态
     * @param userId
     * @param active
     * @return
     */
    @RequestMapping(value = "/user_active",method = RequestMethod.POST)
    public BaseResponse setUserActive(String userId,boolean active){
        return userService.setUserActive(userId,active);
    }

    /**
     * 修改用户密码
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/change_password",method = RequestMethod.POST)
    public BaseResponse changeUserPassword(String userId,String oldPassword,String newPassword){
        return userService.changeUserPassword(userId,oldPassword,newPassword);
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @RequestMapping(value = "delete_user",method = RequestMethod.POST)
    public BaseResponse deleteUser(String userId){
        return userService.deleteUserById(userId);
    }
}
