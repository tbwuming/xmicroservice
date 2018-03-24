package com.xms.usercenter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xms.usercenter.domain.User;
import com.xms.usercenter.service.UserService;

/**
 * url 路径为类上面的路径+方法的路径，如{[/xms/user/{id}]} 和 {[/xms/{name}]}
 */
@RestController
@RequestMapping("/xms/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * http://localhost:8081/xms/users/5
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	/**
	 * http://localhost:8081/xms/users/name/xxoo
	 */
	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public List<User> getUserByName(@PathVariable String name) {
		return userService.getUserByName(name);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String addUser(@RequestBody User user) {
		int ret = userService.addUser(user);
		if (ret > 0) {
			return "success";
		}

		return "failure";
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public String updateUser(@PathVariable Long id, @RequestBody User user) {
		int ret = userService.updateUser(user);
		if (ret > 0) {
			return "success";
		}

		return "failure";
	}
}
