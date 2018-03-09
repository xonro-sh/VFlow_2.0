package com.xonro.vflow.wxpay;

import com.alibaba.fastjson.JSON;
import com.xonro.vflow.wxpay.bean.Coupon;
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
        UnifiedOrder unifiedOrder = new UnifiedOrder("body","tradeNo",200,"openId","clientIp","http://test.notify.url");
        System.out.println(JSON.toJSONString(unifiedOrder));
        System.out.println(JSON.parseObject(JSON.toJSONString(unifiedOrder), Map.class));
    }

    @Test
    public void testToMap(){
        Coupon coupon = new Coupon(){{
            setCouponType("代金券类型");
            setCouponId("代金券id");
            setCouponFee("10000");
        }};

        Map<String,String> map = JSON.parseObject(JSON.toJSONString(coupon),Map.class);
        System.out.println(map);
    }

}
