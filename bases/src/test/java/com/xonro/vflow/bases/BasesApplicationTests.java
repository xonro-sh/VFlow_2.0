package com.xonro.vflow.bases;

import com.xonro.vflow.bases.bean.WechatConf;
import com.xonro.vflow.bases.bean.WxPayConf;
import com.xonro.vflow.bases.helper.ConfManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableCaching
public class BasesApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	ConfManager confManager;

	@Test
	public void testConf(){
		WechatConf wechatConf = new WechatConf();
		wechatConf.setAppId("test app id");
		wechatConf.setAppSecret("test app secret");
		wechatConf.setToken("test token");

		confManager.saveWechatConf(wechatConf);
		wechatConf = confManager.getWechatConf();

		WxPayConf wxPayConf = new WxPayConf();
		wxPayConf.setApiKey("test wxpay key");
		wxPayConf.setBusinessName("test business name");
		wxPayConf.setMchId("test mch_id");
		wxPayConf.setUseSandBox(false);

		confManager.saveWxPayConf(wxPayConf);
		wxPayConf = confManager.getWxPayConf();
		System.out.println(wechatConf);
		System.out.println(wxPayConf);
	}




}
