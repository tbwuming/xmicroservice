package com.xms.usercenter.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xms.domain.ResultDO;
import com.xms.domain.User;
import com.xms.usercenter.service.UserService;

/**
 * url 路径为类上面的路径+方法的路径，如{[/xms/user/{id}]} 和 {[/xms/{name}]}
 */
@RestController
@RequestMapping("/xms/v1/users")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * http://localhost:8081/xms/v1/users/5
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResultDO<User> getUserById(@PathVariable Long id) {
		User user = null;
		try {
			user = userService.getUserById(id);
		} catch (Exception e) {
			logger.error("getUserById error, id={}", id, e);
		}

		logger.info("getUserById success, id={}, user.name={}", id, user.getName());

		ResultDO<User> result = new ResultDO<User>(user);
		return result;
	}

	/**
	 * http://localhost:8081/xms/v1/users/name/xxoo
	 */
	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public ResultDO<List<User>> getUserByName(@PathVariable String name) {
		List<User> userList = null;
		try {
			userList = userService.getUserByName(name);
		} catch (Exception e) {
			logger.error("getUserByName error, name={}", name, e);
		}

		logger.info("getUserByName success, name={}, size={}", name, userList == null ? 0 : userList.size());

		ResultDO<List<User>> result = new ResultDO<List<User>>(userList);
		return result;
	}

	/**
	 * http://localhost:8081/xms/v1/users/
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResultDO<User> addUser(@RequestBody User user) {
		int ret = -1;
		try {
			ret = userService.addUser(user);
		} catch (Exception e) {
			logger.error("addUser error, user={}", user, e);
		}

		ResultDO<User> result = null;
		if (ret > 0) {
			result = new ResultDO<User>(user);
			logger.info("addUser success, user={}", user);
		} else {
			result = new ResultDO<User>(1001, "add user failure");
			logger.info("addUser failure, user={}", user);
		}

		return result;
	}

	/**
	 * http://localhost:8081/xms/v1/users/10
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResultDO<User> updateUser(@PathVariable Long id, @RequestBody User user) {
		user.setId(id); // 有可能json串生成的user里没有id值

		int ret = -1;
		try {
			ret = userService.updateUser(user);
		} catch (Exception e) {
			logger.info("updateUser error, user={}", user, e);
		}
		
		ResultDO<User> result = null;
		if (ret > 0) {
			result = new ResultDO<User>(user);
			logger.info("updateUser success, user={}", user);
		} else {
			result = new ResultDO<User>(1001, "update user failure");
			logger.info("updateUser failure, user={}", user);
		}

		return result;
	}
	
}
