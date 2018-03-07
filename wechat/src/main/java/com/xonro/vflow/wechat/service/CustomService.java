package com.xonro.vflow.wechat.service;

import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.wechat.bean.custom.CustomInfo;
import com.xonro.vflow.wechat.bean.custom.CustomMessageMain;
import com.xonro.vflow.wechat.bean.custom.CustomServiceResult;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

/**
 * 客服业务相关接口
 * 客服消息
 * @author Alex
 * @date 2018/1/10
 */
public interface CustomService {
    /**
     * 增加客服账号 最多能有100个客服
     * @param kfAccount 完整的客服账号 格式为：账号前缀@公众号微信号
     * @param nickName 客服昵称
     * @param password 客服账号登录密码，该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码
     * @throws IOException 异常
     * @throws VFlowException 异常
     * @return 增加客服是否成功
     */
    CustomServiceResult addCustom(@NotNull String kfAccount, @NotNull String nickName, String password) throws IOException, VFlowException;

    /**
     * 修改客服账号
     * @param kfAccount 完整的客服账号 格式为：账号前缀@公众号微信号
     * @param nickName 客服昵称
     * @param password 客服账号登录密码，该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码
     * @throws IOException 异常
     * @throws VFlowException 异常
     * @return 修改客服是否成功
     */
    CustomServiceResult updateCustom(@NotNull String kfAccount, @NotNull String nickName, String password) throws IOException, VFlowException;

    /**
     * 删除客服账号
     * @param kfAccount 完整的客服账号 格式为：账号前缀@公众号微信号
     * @param nickName 客服昵称
     * @param password 客服账号登录密码，该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码
     * @throws IOException 异常
     * @throws VFlowException 异常
     * @return 删除客服是否成功
     */
    CustomServiceResult delCustom(@NotNull String kfAccount, String nickName, String password) throws IOException, VFlowException;

    /**
     * 设置客服账号的头像
     * @param title 标题
     * @param head 头像字节数据
     * @param kfAccount 完整的客服账号 格式为：账号前缀@公众号微信号
     * @throws IOException 异常
     * @throws VFlowException 异常
     * @return 是否成功
     */
    CustomServiceResult uploadHeadImg(String title, byte[] head, String kfAccount) throws IOException, VFlowException;

    /**
     * 获取所有客服账号
     * @throws IOException 异常
     * @throws VFlowException 异常
     * @return 所有客服账号对象
     */
    List<CustomInfo> getKfList() throws IOException, VFlowException;

    /**
     * 客服发消息
     * @param customMessageMain 消息信息（bean）
     * @throws IOException 异常
     * @throws VFlowException 异常
     * @return 消息是否发送成功
     */
    CustomServiceResult customSend(CustomMessageMain customMessageMain) throws IOException, VFlowException;

    /**
     * 客服输入状态
     * @param toUser 普通用户（openid）
     * @param typing "Typing"：对用户下发“正在输入"状态 "CancelTyping"：取消对用户的”正在输入"状态
     * @throws IOException 异常
     * @throws VFlowException 异常
     * @return 是否成功
     */
    CustomServiceResult customTyping(String toUser, String typing) throws IOException, VFlowException;
}
