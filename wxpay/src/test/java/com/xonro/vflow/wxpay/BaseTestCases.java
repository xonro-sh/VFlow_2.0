package com.xonro.vflow.wxpay;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPayUtil;
import com.xonro.vflow.wxpay.bean.Coupon;
import com.xonro.vflow.wxpay.bean.order.UnifiedOrder;
import com.xonro.vflow.wxpay.bean.pay.PayNotify;
import org.junit.Test;

import java.util.HashMap;
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

    @Test
    public void testInitMap(){
        Map<String,String> map = new HashMap<String, String>(8){{
            put("bill_date","billDate");
            put("bill_type","billType");
            put("tar_type","tarType");
        }};

        System.out.println(map);
    }

    @Test
    public void testXml2Json() throws Exception {
        String xml = "<xml>\n" +
                "  <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "  <attach><![CDATA[支付测试]]></attach>\n" +
                "  <bank_type><![CDATA[CFT]]></bank_type>\n" +
                "  <fee_type><![CDATA[CNY]]></fee_type>\n" +
                "  <is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
                "  <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "  <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>\n" +
                "  <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>\n" +
                "  <out_trade_no><![CDATA[1409811653]]></out_trade_no>\n" +
                "  <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "  <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>\n" +
                "  <sub_mch_id><![CDATA[10000100]]></sub_mch_id>\n" +
                "  <time_end><![CDATA[20140903131540]]></time_end>\n" +
                "  <total_fee>1</total_fee>\n" +
                "<coupon_fee><![CDATA[100]]></coupon_fee>\n" +
                "<coupon_count><![CDATA[1]]></coupon_count>\n" +
                "<coupon_type><![CDATA[CASH]]></coupon_type>\n" +
                "<coupon_id><![CDATA[10000]]></coupon_id>\n" +
                "<coupon_fee_0><![CDATA[100]]></coupon_fee_0>\n" +
                "  <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "  <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>\n" +
                "</xml> ";
        String map = JSON.toJSONString(WXPayUtil.xmlToMap(xml));
        System.out.println(JSON.parseObject(map, PayNotify.class));

    }

}
