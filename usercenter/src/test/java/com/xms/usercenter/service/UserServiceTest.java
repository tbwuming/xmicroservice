package com.xms.usercenter.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xms.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) 
public class UserServiceTest {
	
	@Autowired
	private UserService userService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_getUserById() {
		User user = userService.getUserById(1L);
		Assert.assertTrue(user != null && user.getId() == 1L);
	}
	
	@Test
	public void test_getUserByName() {
		List<User> userList = userService.getUserByName("jimmy");
		for (User user : userList) {
			Assert.assertTrue(user != null && "jimmy".equals(user.getName()));
		}
	}
	
	@Test
	public void test_addUser() {
		User user = new User();
		user.setName("xxoo");
		user.setAge(16);
		user.setAddress("马连洼北路");
		user.addFeature("school", "1");
		
		int ret = userService.addUser(user);
		Assert.assertTrue(ret == 1);
	}
	
	@Test
	public void test_updateUser() {
		List<User> userList = userService.getUserByName("xxoo");
		for (User user : userList) {
			user.setAddress("马连洼北路too");
			user.addFeature("hornor", "2");
			
			int ret = userService.updateUser(user);
			Assert.assertTrue(ret == 1);
		}
		
	}

}
