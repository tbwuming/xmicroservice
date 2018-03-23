package com.xms.usercenter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xms.usercenter.dao.UserDao;
import com.xms.usercenter.domain.User;
import com.xms.usercenter.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User getUserById(Long id) {
		return userDao.getUserById(id);
	}

	@Override
	public List<User> getUserByName(String name) {
		return userDao.getUserByName(name);
	}

	@Override
	public int addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

}
