package com.xms.usercenter.service;

import java.util.List;

import com.xms.domain.User;

public interface UserService {

	User getUserById(Long id);

	List<User> getUserByName(String name);

	int addUser(User user);

	int updateUser(User user);
}
