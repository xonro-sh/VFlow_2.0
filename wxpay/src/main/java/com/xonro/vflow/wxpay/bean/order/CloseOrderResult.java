package com.xonro.vflow.wxpay.bean.order;

import com.alibaba.fastjson.annotation.JSONField;
import com.xonro.vflow.wxpay.bean.WxPayResponse;

import java.io.Serializable;

/**
 * 关闭订单结果模型
 * @author Alex
 * @date 2018/1/3
 */
public class CloseOrderResult extends WxPayResponse implements Serializable{
    /**
     * 业务结果描述
     */
    @JSONField(name = "result_msg")
    private String resultMsg;

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
