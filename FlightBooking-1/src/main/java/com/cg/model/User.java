package com.cg.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "User_Table")
public class User {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq1" )
	@SequenceGenerator(name="seq1", sequenceName = "user_sequence", allocationSize = 1, initialValue = 1)
	private Integer userId;
	
	@Column(name = "User_Name", length = 25, nullable = false)
	private String userName;
	
	@Column(name = "Contact_number", length = 10, unique = true)
	private String phoneNumber;
	
	@Column(name = "Email_ID", unique = true, nullable = false)
	private String email;
	
	@Column(name = "Location")
	private String location;
	
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
