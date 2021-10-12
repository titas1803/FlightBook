package com.cg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.Dto.UserDto;
import com.cg.exceptions.UserException;
import com.cg.exceptions.ValidationException;
import com.cg.model.User;
import com.cg.service.UserService;
import com.cg.util.FlightBookingConstants;
import com.cg.util.SuccessMessage;

public class UserController {
	
	@Autowired
	private UserService userSer;
	
	@PostMapping("createuser")
	public SuccessMessage createUser(@Valid @RequestBody UserDto userDto, BindingResult br) throws ValidationException, UserException {
		if(br.hasErrors())
			throw new ValidationException(br.getFieldErrors());
		User createdUser=userSer.addUser(userDto);
		return new SuccessMessage(FlightBookingConstants.USER_NOT_FOUND+ createdUser.getUserId());
	}
	
	
}
