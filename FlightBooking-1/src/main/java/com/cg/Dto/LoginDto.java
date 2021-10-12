package com.cg.Dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.cg.util.FlightBookingConstants;



public class LoginDto {
	
	@NotNull(message= FlightBookingConstants.USER_ID_REQUIRED)
	private Integer userId;
	
	@NotBlank(message = FlightBookingConstants.PASSWORD_REQUIRED)
	private String password;
	
	@NotBlank(message = FlightBookingConstants.ROLE_REQUIRED)
	private String role;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	

}
