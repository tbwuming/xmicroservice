package com.xms.usercenter.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xms.domain.User;
import com.xms.usercenter.dao.UserDao;
import com.xms.usercenter.mapper.UserMapper;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUserById(Long id) {
		return userMapper.selectUserById(id);
	}

	@Override
	public List<User> getUserByName(String name) {
		return userMapper.selectUserByName(name);
	}

	@Override
	public int addUser(User user) {
		return userMapper.insertUser(user);
	}

	@Override
	public int updateUser(User user) {
		return userMapper.updateUser(user);
	}

}
