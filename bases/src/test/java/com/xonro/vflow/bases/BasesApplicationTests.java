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
	private ConfManager confManager;

	@Test
	public void testConfManager(){
		WechatConf wechatConf = new WechatConf();
		wechatConf.setAppId("appid2");
		wechatConf.setAppSecret("appSercret");
		wechatConf.setToken("token");

		confManager.saveWechatConf(wechatConf);

		System.out.println(wechatConf);
	}

	@Test
	public void testCacheConf(){
		WechatConf wechatConf = confManager.getWechatConf();
		System.out.println(wechatConf);
	}

	@Test
	public void testWxpayConf(){
		WxPayConf wxPayConf = confManager.getWxPayConf();
		System.out.println(wxPayConf);
	}

	@Test
	public void testSaveWxPayConf(){
		WxPayConf wxPayConf = new WxPayConf();
		wxPayConf.setBusinessName("测试商户2");
		wxPayConf.setMchId("1301264401");
		wxPayConf.setKey("41915360030255580443224118959153");

		System.out.println(confManager.saveWxPayConf(wxPayConf));
	}

}
