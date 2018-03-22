package com.xonro.vflow.workflow;

import com.xonro.vflow.workflow.bean.UserInfo;
import com.xonro.vflow.workflow.service.UserService;
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
	private UserService userService;

	@Test
	public void testSaveUser(){
		List<User> userList = userService.listPage(0,10);
		String userId = userList.get(0).getId();
		userService.saveUser(userId,"ma","jack","jack@test.com","test");

		UserInfo userInfo = new UserInfo(){{
			setUserId(userId);
			setActive(true);
			setEmployeeState("在职");
			setEmployeeType("全职");
			setPosition("工程师");
			setRemark("无");
		}};

		System.out.println(userService.saveUserInfo(userInfo));
	}

	@Test
	public void getUserInfo(){
		System.out.println(userService.getUserInfo("fe938166-f268-4de4-9e72-5a428f51e651"));
	}

	@Autowired
	private IdentityService identityService;
	@Test
	public void testIdGenerator(){
		identityService.deleteUserInfo("fe938166-f268-4de4-9e72-5a428f51e651","positionLevel");
		identityService.setUserInfo("fe938166-f268-4de4-9e72-5a428f51e651","positionLevel","1");
	}

}
