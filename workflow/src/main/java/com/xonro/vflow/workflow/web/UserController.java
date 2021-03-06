package com.xonro.vflow.workflow.web;

import com.xonro.vflow.bases.bean.BaseResponse;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.workflow.bean.CreateUser;
import com.xonro.vflow.workflow.bean.Department;
import com.xonro.vflow.workflow.bean.Role;
import com.xonro.vflow.workflow.bean.UserInfo;
import com.xonro.vflow.workflow.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.identity.User;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

/**
 * 人员相关控制器
 * @author louie
 * @date created in 2018-4-12 16:12
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public User createUser(@Valid @RequestBody CreateUser user) throws VFlowException {
        return userService.createUser(user);
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public User findUserById(String userId){
        return userService.findUserById(userId);
    }

    @RequestMapping(value = "/single")
    public void get(String userId, HttpServletResponse response) throws IOException {
        response.sendRedirect("/identity/users/"+userId);
    }
    /**
     * 设置用户状态
     * @param userId
     * @param active
     * @return
     */
    @RequestMapping(value = "/active",method = RequestMethod.POST)
    public BaseResponse setUserActive(String userId,boolean active) throws VFlowException {
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
     * 更新用户信息
     * @param userId 用户id
     * @param firstName 姓
     * @param lastName 名
     * @param email 邮箱
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public User updateUser(String userId,String firstName,String lastName,String email) throws VFlowException {
        return userService.updateUser(userId,firstName,lastName,email);
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @RequestMapping(value = "/delete")
    public BaseResponse deleteUser(String userId){
        return userService.deleteUserById(userId);
    }

    /**
     * 获取用户详细信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/info_get")
    public UserInfo getUserInfo(@NotBlank(message = "userId can not be empty") String userId) throws VFlowException, IllegalAccessException {
        return userService.getUserInfo(userId);
    }

    /**
     * 保存用户信息
     * @param userInfo
     * @return
     * @throws IllegalAccessException
     * @throws VFlowException
     */
    @RequestMapping(value = "/info_save")
    public UserInfo saveUserInfo(@Valid @RequestBody UserInfo userInfo) throws IllegalAccessException, VFlowException {
        return userService.saveUserInfo(userInfo);
    }

    /**
     * 设置用户部门
     * @param userId
     * @param departmentId
     * @return
     * @throws VFlowException
     */
    @RequestMapping(value = "/set_department")
    public BaseResponse setDepartment(@NotBlank(message = "userId can not be empty") String userId,
                                      @NotBlank(message = "departmentId can not be empty") String departmentId) throws VFlowException {
        return userService.setUserDepartment(userId,departmentId);
    }

    /**
     * 获取用户所属部门
     * @param userId
     * @return
     */
    @RequestMapping(value = "/department")
    public Department userDepartment(String userId){
        return userService.userDepartment(userId);
    }

    /**
     * 设置用户角色
     * @param userId
     * @param roleId
     * @return
     * @throws VFlowException
     */
    @RequestMapping(value = "/set_role")
    public BaseResponse setRole(@NotBlank(message = "userId can not be empty") String userId,
                                @NotBlank(message = "roleId can not be empty") String roleId) throws VFlowException {
        return userService.setUserRole(userId,roleId);
    }

    /**
     * 获取用户角色
     * @param userId
     * @return
     */
    @RequestMapping(value = "/role")
    public Role userRole(@NotBlank(message = "userId can not be empty") String userId){
        return userService.getUserRole(userId);
    }

    /**
     * 用户登录
     * @param userId 用户账号
     * @param password 用户密码
     * @param session
     * @return 成功则返回用户信息，失败抛出异常，异常信息为失败原因
     * @throws VFlowException
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public User login(String userId, String password, HttpSession session) throws VFlowException {
        User user = userService.login(userId,password);
        session.setAttribute(session.getId(),user);
        return user;
    }

    /**
     * 用户登出
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout")
    public BaseResponse logout(HttpSession session){
        session.removeAttribute(session.getId());
        return new BaseResponse(true,"success","");
    }

}
