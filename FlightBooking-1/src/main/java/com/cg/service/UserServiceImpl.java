package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.Dto.UserDto;
import com.cg.exceptions.UserException;
import com.cg.model.Login;
import com.cg.model.User;
import com.cg.repository.LoginRepository;
import com.cg.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private LoginService loginSer;
	
	@Autowired
	private LoginRepository loginRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public User addUser(UserDto userDto) throws UserException {
		
		Optional<User> optUserbyCon= userRepo.findByContact(userDto.getPhoneNumber());
		if(optUserbyCon.isPresent()) {
			throw new UserException("User with same phone no. Already exists");
		}
		
		Optional<User> optUserbyEmail= userRepo.findByEmail(userDto.getEmail());
		if(optUserbyEmail.isPresent()) {
			throw new UserException("User with same emailId Already exists");
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
	public List<User> viewAllUser() throws UserException{
		List<User> usrlst = userRepo.findAll();
		if(usrlst.isEmpty())
			throw new UserException("No User Found");
		usrlst.sort((u1,u2)-> u1.getUserName().compareTo(u2.getUserName()));
		return usrlst;
		
	}
	
	@Override
	public User viewUserbyId(Integer userId) throws UserException{
		Optional<User> optUser = userRepo.findById(userId);
		if (!optUser.isPresent()) {
			throw new UserException("User Not Found");
		}
		return optUser.get();
	}
	
	@Override
	public String deleteUser(Integer userId) throws UserException{
		Optional<User> optUser = userRepo.findById(userId);
		if (!optUser.isPresent()) {
			throw new UserException("User Not Found");
		}
		userRepo.deleteById(userId);
		return "User deleted with Id "+userId;
	}
}
