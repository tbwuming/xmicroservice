package com.xms.usercenter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xms.domain.ResultDO;
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
	 * http://localhost:8081/xms/v1/users/5
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResultDO<User> getUserById(@PathVariable Long id) {
		User user = userService.getUserById(id);
		return new ResultDO<User>(user);
	}

	/**
	 * http://localhost:8081/xms/v1/users/name/xxoo
	 */
	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public ResultDO<List<User>> getUserByName(@PathVariable String name) {
		List<User> userList = userService.getUserByName(name);
		return new ResultDO<List<User>>(userList);
	}

	/**
	 * http://localhost:8081/xms/v1/users/
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResultDO<User> addUser(@RequestBody User user) {
		ResultDO<User> result = null;
		int ret = userService.addUser(user);
		if (ret > 0) {
			result = new ResultDO<User>(user);
		} else {
			result = new ResultDO<User>(1001, "add user failure");
		}

		return result;
	}

	/**
	 * http://localhost:8081/xms/v1/users/10
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResultDO<User> updateUser(@PathVariable Long id, @RequestBody User user) {
		user.setId(id);	// 有可能json串生成的user里没有id值
		
		ResultDO<User> result = null;
		int ret = userService.updateUser(user);
		if (ret > 0) {
			result = new ResultDO<User>(user);
		} else {
			result = new ResultDO<User>(1001, "update user failure");
		}

		return result;
	}
}
