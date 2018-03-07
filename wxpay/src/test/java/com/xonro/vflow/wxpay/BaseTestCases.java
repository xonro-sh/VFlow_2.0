package com.xonro.vflow.wxpay;

import com.alibaba.fastjson.JSON;
import com.xonro.vflow.wxpay.bean.order.UnifiedOrder;
import org.junit.Test;

import java.util.Map;

/**
 * @author louie
 * @date created in 2018-3-7 16:22
 */
public class BaseTestCases {
    @Test
    public void testJsonString(){
        UnifiedOrder unifiedOrder = new UnifiedOrder("body","tradeNo",200,"openId","clientIp");
        System.out.println(JSON.toJSONString(unifiedOrder));
        System.out.println(JSON.parseObject(JSON.toJSONString(unifiedOrder), Map.class));
    }
}
