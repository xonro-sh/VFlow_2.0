package com.xonro.vflow.wechat.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.wechat.exception.WechatException;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建二维码请求
 * @author louie
 * @date 2018-1-14
 */
public class CreateQrCode {
    /**
     * 该二维码有效时间，以秒为单位，最大不超过2592000（即30天），小于等于0时创建永久二维码
     */
    @JSONField(name = "expireSeconds")
    private Long expireSeconds;

    /**
     * 二维码类型
     */
    @JSONField(name = "action_name")
    private String actionName;

    /**
     * 二维码详细信息
     */
    @JSONField(name = "actionInfo")
    private Map<String,Map<String,Object>> actionInfo;

    /**
     * 构建创建二维码的请求模型
     * @param expireSeconds 二维码有效时长
     * @param scene 场景值
     */
    public CreateQrCode(Long expireSeconds,Object scene) throws VFlowException {
        //场景参数
        Map<String,Object> sceneMap = new HashMap<>();
        if (scene instanceof String){
            if (expireSeconds <= 0){
                //二维码类型:永久的字符串参数值
                this.actionName = "QR_LIMIT_STR_SCENE";
            }else {
                //二维码类型:临时的字符串参数值
                this.actionName ="QR_STR_SCENE";
                this.expireSeconds = expireSeconds;
            }
            sceneMap.put("scene_str",scene);
        }else if (scene instanceof Integer){
            if (expireSeconds <= 0){
                //二维码类型:永久的整型参数值
                this.actionName = "QR_LIMIT_SCENE";
            }else {
                //二维码类型:临时的整型参数值
                this.actionName ="QR_SCENE";
                this.expireSeconds = expireSeconds;
            }
            sceneMap.put("scene_id",scene);
        }else {
            throw new VFlowException("error parameter","scene type only can be String or Integer");
        }

        this.actionInfo = new HashMap<>();
        this.actionInfo.put("scene",sceneMap);
    }

    public Long getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(Long expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Map<String, Map<String, Object>> getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(Map<String, Map<String, Object>> actionInfo) {
        this.actionInfo = actionInfo;
    }
}
