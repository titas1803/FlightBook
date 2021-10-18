package com.cg.Dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.cg.util.FlightBookingConstants;

public class UserDto {

	private Integer userId;

	@NotBlank(message = FlightBookingConstants.USERNAME_REQUIRED)
	private String userName;

	@Length(min = 10, max = 10, message = FlightBookingConstants.INVALID_PHONE_NUMBER)
	@NotBlank(message = FlightBookingConstants.INVALID_PHONE_NUMBER)
	private String phoneNumber;

	@NotBlank
	@Email(message = FlightBookingConstants.INVALID_EMAILID)
	private String email;

	private String location;

	@NotBlank(message = FlightBookingConstants.PASSWORD_REQUIRED)
	private String password;

	
	@NotBlank(message = FlightBookingConstants.ROLE_REQUIRED)
	@Pattern(regexp = "(user|admin)", message = FlightBookingConstants.USER_OR_ADMIN)
	private String role;
	
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}