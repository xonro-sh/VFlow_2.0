package com.xonro.vflow.wechat.web.controller;

import com.alibaba.fastjson.JSON;
import com.xonro.vflow.bases.bean.BaseResponse;
import com.xonro.vflow.bases.bean.WechatConf;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.wechat.bean.WechatJsSignature;
import com.xonro.vflow.wechat.bean.message.Message;
import com.xonro.vflow.wechat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 微信公众平台相关服务控制器
 * @author louie modified by Alex
 * @date 2018-1-3
 */
@EnableAutoConfiguration
@RestController
@RequestMapping(value = "/wechat")
public class WechatController {
    private final TokenService tokenService;
    private final JsApiService jsApiService;
    private final MessageService messageService;
    private final WechatService wechatService;
    private final UserService userService;

    @Autowired
    public WechatController(TokenService tokenService, JsApiService jsApiService, MessageService messageService, WechatService wechatService, UserService userService) {
        this.tokenService = tokenService;
        this.jsApiService = jsApiService;
        this.messageService = messageService;
        this.wechatService = wechatService;
        this.userService = userService;
    }

    /**
     * 微信平台服务器配置签名认证
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param echostr 随机字符串
     * @return 签名认证成功 返回：echostr 随机字符串 失败返回其他
     */
    @RequestMapping(value = "/access",method = RequestMethod.GET)
    public String checkSignature(String signature, Long timestamp, String nonce, String echostr){
        return tokenService.checkSignature(signature,timestamp,nonce,echostr);
    }

    /**
     * 生成指定url地址的js signature
     * @param rquestPage 请求页面
     * @return js签名
     */
    @RequestMapping(value = "/jsSignature")
    public WechatJsSignature getJsSignature(String rquestPage){
        return jsApiService.getSignature(rquestPage);
    }

    /**
     * 微信平台消息、事件接收接口
     * @param message 微信平台post的消息xml
     * @return 接收状态
     */
    @RequestMapping(value = "/access",method = RequestMethod.POST)
    public String accessEvent(@RequestBody String message){
        return messageService.parseMessage(message);
    }

    /**
     * 更新微信服务号配置
     * @param data json数据
     * @return 结果
     */
    @RequestMapping(value = "/updateServiceNoConf")
    public BaseResponse updateServiceNoConf(String data){
        return wechatService.updateServiceNoConf(JSON.parseObject(data, WechatConf.class));
    }

    /**
     * 获取微信服务号配置
     * @return 结果
     */
    @RequestMapping(value = "/getServiceNoConf")
    public BaseResponse getServiceNoConf(){
        return wechatService.getServiceNoConf();
    }

    /**
     * 获取消息配置
     * @param type 消息类型（大类）
     * @return 结果
     */
    @RequestMapping(value = "/getMessageConf")
    public BaseResponse getMessageConf(String type){
        return wechatService.getMessageConf(type);
    }

    /**
     * 更新消息设置
     * @param data 信息对象
     * @return 结果
     */
    @RequestMapping(value = "/updateMessageConf")
    public BaseResponse updateMessageConf(String data){
        return wechatService.updateMessageConf(JSON.parseObject(data, Message.class));
    }

    @RequestMapping(value = "/getUserInfoList")
    public String getUserInfoList(Integer page, Integer limit){
        return JSON.toJSONString(userService.getUserInfoList("", page, limit));
    }

    /**
     * 更新用户备注
     * @param openId 普通用户的标识，对当前公众号唯一
     * @param remark 备注
     * @return
     */
    @RequestMapping(value = "/updateRemark")
    public BaseResponse updateRemark(String openId, String remark){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setOk(true);
        try {
            userService.updateRemark(openId, remark);
            return baseResponse;
        } catch (VFlowException | IOException e) {
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }
}
