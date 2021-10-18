package com.cg.service;

import java.util.List;

import com.cg.Dto.UserDto;
import com.cg.exceptions.AlreadyExistsException;
import com.cg.exceptions.NotFoundException;
import com.cg.model.User;

public interface UserService {
	
	public User addUser(UserDto userDto) throws AlreadyExistsException;

	List<User> viewAllUser() throws NotFoundException;

	User viewUserbyId(Integer userId) throws NotFoundException;

	String deleteUser(Integer userId) throws NotFoundException;

}
