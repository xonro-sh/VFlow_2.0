package com.xonro.vflow.wechat.service;


import com.xonro.vflow.bases.bean.TableResponse;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.wechat.bean.user.UserInfo;

import java.io.IOException;

/**
 * 用户管理相关业务服务接口
 * @author louie
 * @date 2018-01-11
 */
public interface WechatUserService {

    /**
     * 获取用户基本信息
     * @param openId 普通用户的标识，对当前公众号唯一
     * @throws VFlowException 异常
     * @throws IOException 异常
     * @return 结果
     */
    public UserInfo getUserInfo(String openId) throws VFlowException, IOException;

    /**
     * 保存用户信息
     * @param userInfo 需保存的用户对象，可通过获取用户信息接口获得
     */
    public void saveUser(UserInfo userInfo);

    /**
     * 删除用户
     * @param userInfo 需删除的用户信息
     */
    public void deleteUser(UserInfo userInfo);

    /**
     * 批量获取用户基本信息
     * @param openId 普通用户的标识，对当前公众号唯一
     * @param page 第几页
     * @param rows 每页多少条数据
     * @return 结果
     */
    public TableResponse getUserInfoList(String openId, Integer page, Integer rows);


    /**
     * 更新备注
     * @param openId 普通用户的标识，对当前公众号唯一
     * @param remark 备注信息
     * @throws VFlowException 异常
     * @throws IOException 异常
     */
    public void updateRemark(String openId, String remark) throws VFlowException, IOException;


}
