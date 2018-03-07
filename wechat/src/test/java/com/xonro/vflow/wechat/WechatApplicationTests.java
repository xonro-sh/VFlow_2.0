package com.xonro.vflow.wechat;

import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.bases.helper.RequestExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testRequestExe() throws IOException, VFlowException {
		String result = new RequestExecutor("https://www.baidu.com").execute().getResponseAsObject(String.class);
		System.out.println(result);
	}
}
