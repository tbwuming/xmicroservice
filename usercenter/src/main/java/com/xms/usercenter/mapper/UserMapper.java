package com.xms.usercenter.mapper;

import java.util.List;

import com.xms.usercenter.domain.User;

public interface UserMapper {
	
	User selectUserById(Long id);

	List<User> selectUserByName(String name);
	
	int insertUser(User user);
	
	int updateUser(User user);
}
