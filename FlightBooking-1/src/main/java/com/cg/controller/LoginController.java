package com.cg.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.util.LoginResponse;

public class LoginController {
	
	@PostMapping("login")
	public LoginResponse doLogin(@Valid @RequestBody )
}
