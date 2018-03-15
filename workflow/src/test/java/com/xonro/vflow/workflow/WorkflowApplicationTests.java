package com.xonro.vflow.workflow;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkflowApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private IdentityService identityService;

	@Test
	public void testSaveUser(){
//		User user = identityService.newUser("fe938166-f268-4de4-9e72-5a428f51e651");
//		user.setId(null);

		List<User> userList = identityService.createUserQuery().listPage(0,10);
		System.out.println(userList.size());
	}

}
