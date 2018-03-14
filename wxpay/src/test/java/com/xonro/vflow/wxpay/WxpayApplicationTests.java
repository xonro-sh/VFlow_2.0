package com.xonro.vflow.wxpay;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPayConfig;
import com.xonro.vflow.wxpay.bean.order.QueryOrderResult;
import com.xonro.vflow.wxpay.bean.order.UnifiedOrderResult;
import com.xonro.vflow.wxpay.service.OrderService;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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

	@Test
	public void testQueryOrder(){
		QueryOrderResult queryOrderResult = orderService.queryOrderByTradeId("tradeid");
		System.out.println(JSON.toJSONString(queryOrderResult));
	}

	@Autowired
	private WXPayConfig wxPayConfig;
	@Test
	public void testCertInputStream() throws IOException {
		InputStream inputStream = wxPayConfig.getCertStream();
		FileUtils.copyToFile(inputStream,new File("test.p12"));
	}

}
