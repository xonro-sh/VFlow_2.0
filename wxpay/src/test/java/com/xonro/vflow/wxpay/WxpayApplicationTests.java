package com.xonro.vflow.wxpay;

import com.alibaba.fastjson.JSON;
import com.xonro.vflow.wxpay.bean.order.UnifiedOrderResult;
import com.xonro.vflow.wxpay.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableCaching
@ComponentScan(basePackages = "com.xonro.vflow")
public class WxpayApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	OrderService orderService;
	@Test
	public void testOrderService(){
		UnifiedOrderResult result = orderService.unifiedOrder("test body","test_trade_no",100,"test_open_id");
		System.out.println(JSON.toJSONString(result));
	}

}
