package com.xms.usercenter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xms.usercenter.domain.User;
import com.xms.usercenter.service.UserService;

/**
 * url 路径为类上面的路径+方法的路径，如{[/xms/user/{id}]} 和 {[/xms/{name}]}
 */
@RestController
@RequestMapping("/xms")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * http://localhost:8081/xms/user/5
	 */
	@RequestMapping("/user/{id}")
	public User findUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	/**
	 * http://localhost:8081/xms/xxoo
	 */
	@RequestMapping("{name}")
	public List<User> findUserById(@PathVariable String name) {
		return userService.getUserByName(name);
	}
}
