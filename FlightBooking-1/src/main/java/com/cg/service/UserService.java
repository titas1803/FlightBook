package com.cg.service;

import java.util.List;

import com.cg.Dto.UserDto;
import com.cg.exceptions.UserException;
import com.cg.model.User;

public interface UserService {
	
	public User addUser(UserDto userDto) throws UserException;

	List<User> viewAllUser() throws UserException;

	User viewUserbyId(Integer userId) throws UserException;

	String deleteUser(Integer userId) throws UserException;

}
