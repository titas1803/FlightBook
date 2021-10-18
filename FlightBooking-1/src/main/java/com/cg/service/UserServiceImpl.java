package com.cg.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.Dto.UserDto;
import com.cg.exceptions.AlreadyExistsException;
import com.cg.exceptions.NotFoundException;
import com.cg.model.Login;
import com.cg.model.User;
import com.cg.repository.LoginRepository;
import com.cg.repository.UserRepository;
import com.cg.util.FlightBookingConstants;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private LoginService loginSer;
	
	@Autowired
	private LoginRepository loginRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	@Transactional
	public User addUser(UserDto userDto) throws AlreadyExistsException {
		
		Optional<User> optUserbyCon= userRepo.findByContact(userDto.getPhoneNumber());
		if(optUserbyCon.isPresent()) {
			throw new AlreadyExistsException(FlightBookingConstants.PHONE_NUMBER_ALREADY_EXISTS);
		}
		
		Optional<User> optUserbyEmail= userRepo.findByEmail(userDto.getEmail());
		if(optUserbyEmail.isPresent()) {
			throw new AlreadyExistsException(FlightBookingConstants.EMAILID_ALREADY_EXISTS);
		}
		
		User user= new User();
		user.setUserName(userDto.getUserName());
		user.setPhoneNumber(userDto.getPhoneNumber());
		user.setEmail(userDto.getEmail());
		user.setLocation(userDto.getLocation());
		User persistedUser = userRepo.saveAndFlush(user);
		Login login = new Login();
		login.setUser(persistedUser);
		login.setPassword(loginSer.encryptString(userDto.getPassword()));
		login.setRole(userDto.getRole());
		loginRepo.save(login);
		return persistedUser;
	}
	
	@Override
	public List<User> viewAllUser() throws NotFoundException{
		List<User> usrlst = userRepo.findAll();
		if(usrlst.isEmpty())
			throw new NotFoundException(FlightBookingConstants.USER_NOT_FOUND);
		usrlst.sort((u1,u2)-> u1.getUserName().compareTo(u2.getUserName()));
		return usrlst;
		
	}
	
	@Override
	public User viewUserbyId(Integer userId) throws NotFoundException{
		Optional<User> optUser = userRepo.findById(userId);
		if (!optUser.isPresent()) {
			throw new NotFoundException(FlightBookingConstants.USER_ID_NOT_FOUND);
		}
		return optUser.get();
	}
	
	@Override
	public String deleteUser(Integer userId) throws NotFoundException{
		Optional<User> optUser = userRepo.findById(userId);
		if (!optUser.isPresent()) {
			throw new NotFoundException(FlightBookingConstants.USER_ID_NOT_FOUND);
		}
		userRepo.deleteById(userId);
		return FlightBookingConstants.SUCCESSFULLY_DELETED+userId;
	}
}
