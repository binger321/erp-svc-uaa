package com.binger.uaa;

import com.binger.uaa.domain.User;
import com.binger.uaa.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ErpSvcUaaApplicationTests {

	@Autowired
	private UserService userService;
	@Test
	public void contextLoads() {
		User user = userService.findUserByName("chenjingyi");
		System.out.println(user);
	}

}
