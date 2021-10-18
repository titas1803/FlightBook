package com.cg.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cg.Dto.UserDto;
import com.cg.exceptions.AlreadyExistsException;
import com.cg.exceptions.LoginException;
import com.cg.exceptions.NotFoundException;
import com.cg.exceptions.ValidationException;
import com.cg.model.User;
import com.cg.service.LoginService;
import com.cg.service.UserService;
import com.cg.util.FlightBookingConstants;

@RestController
public class UserController {
	
	@Autowired
	private UserService userSer;
	
	@Autowired
	private LoginService loginSer;
	
	@PostMapping("createuser")
	public User createUser(@Valid @RequestBody UserDto userDto, BindingResult br) throws ValidationException, AlreadyExistsException {
		if(br.hasErrors())
			throw new ValidationException(br.getFieldErrors());
		User createdUser=userSer.addUser(userDto);
		return createdUser;
	}
	
	@GetMapping("viewusers")
	public List<User> viewAllUsers(@RequestHeader("token-id") String tokenid) throws LoginException, NotFoundException{
		if(loginSer.verifyLogin(tokenid) && loginSer.verifyRole(tokenid)){
			return userSer.viewAllUser();
		}
		throw new LoginException(FlightBookingConstants.INVALID_TOKEN);
	}
	
	@GetMapping("viewuserbyid/{userId}")
	public User viewbyId(@PathVariable("userId") Integer userId, @RequestHeader("token-id") String tokenId) throws LoginException, NotFoundException{
		if(loginSer.verifyLogin(tokenId)) {
			return userSer.viewUserbyId(userId);
		}
		throw new LoginException(FlightBookingConstants.INVALID_TOKEN);
	}
	
}
