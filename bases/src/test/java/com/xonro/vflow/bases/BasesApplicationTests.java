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
		wechatConf.setAppId("wx578001873c8e2895");
		wechatConf.setAppSecret("3a758b63555f66a397c054e100ae04c5");
		wechatConf.setToken("xonro");

		confManager.saveWechatConf(wechatConf);
		wechatConf = confManager.getWechatConf();

		WxPayConf wxPayConf = new WxPayConf();
		wxPayConf.setApiKey("41915360030255580443224118959153");
		wxPayConf.setBusinessName("威渡财行");
		wxPayConf.setMchId("1301264401");
		wxPayConf.setUseSandBox(true);

		confManager.saveWxPayConf(wxPayConf);
		wxPayConf = confManager.getWxPayConf();
		System.out.println(wechatConf);
		System.out.println(wxPayConf);
	}




}
