package com.xms.usercenter.dao;

import java.util.List;

import com.xms.domain.User;

public interface UserDao {

	User getUserById(Long id);

	List<User> getUserByName(String name);

	int addUser(User user);

	int updateUser(User user);

}
