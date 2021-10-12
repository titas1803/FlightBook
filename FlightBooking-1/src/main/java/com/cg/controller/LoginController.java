package com.cg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cg.Dto.LoginDto;
import com.cg.exceptions.LoginException;
import com.cg.exceptions.ValidationException;
import com.cg.model.Login;
import com.cg.service.LoginService;
import com.cg.util.FlightBookingConstants;
import com.cg.util.LoginResponse;
import com.cg.util.SuccessMessage;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginSer;
	
	@PostMapping("login")
	public LoginResponse doLogin(@Valid @RequestBody LoginDto loginDto, BindingResult br) throws LoginException, ValidationException {
		
		if(br.hasErrors())
			throw new ValidationException(br.getFieldErrors());
		Login login = loginSer.doLogin(loginDto.getUserId(), loginDto.getPassword());
		LoginResponse response = new LoginResponse();
		response.setUserId(login.getUserId());
		response.setUserName(login.getUser().getUserName());
		response.setRole(login.getRole());
		response.setToken(loginSer.generateToken(login));
		return response;
	}
	
	@GetMapping("logout")
	public SuccessMessage logout(@RequestHeader("token-id") String token) {
		loginSer.getAuthMap().remove(token);
		return new SuccessMessage(FlightBookingConstants.LOGGED_OUT);
	}
}







