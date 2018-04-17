package com.xonro.vflow.workflow.web;

import com.xonro.vflow.bases.bean.BaseResponse;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.workflow.bean.CreateUser;
import com.xonro.vflow.workflow.bean.UserInfo;
import com.xonro.vflow.workflow.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.identity.User;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Set;

/**
 * 组织相关控制器
 * @author louie
 * @date created in 2018-4-12 16:12
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/user")
public class OrganizationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public User createUser(@Valid CreateUser user){
        return userService.createUser(user);
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
    public UserInfo getUserInfo(@NotBlank(message = "userId can not be empty") String userId) throws VFlowException {
        return userService.getUserInfo(userId);
    }

    @RequestMapping(value = "/info_save")
    public UserInfo saveUserInfo(@Valid UserInfo userInfo){
        return userService.saveUserInfo(userInfo);
    }

    @ExceptionHandler
    public BaseResponse handleException(Exception e){
        String msg = "";
        if (e instanceof ConstraintViolationException){
            ConstraintViolationException violationException = (ConstraintViolationException)e;
            Set<ConstraintViolation<?>> violations = violationException.getConstraintViolations();
            StringBuilder builder = new StringBuilder();
            for (ConstraintViolation<?> violation : violations) {
                builder.append(violation.getMessage()+";");
            }
            msg = builder.toString();
        }else {
            msg = e.getMessage();
        }
        log.error(msg,e);
        return new BaseResponse(false,"fail",msg);
    }
}
