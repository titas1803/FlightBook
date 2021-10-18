package com.cg.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.exceptions.LoginException;
import com.cg.model.Login;
import com.cg.repository.LoginRepository;
import com.cg.util.FlightBookingConstants;
import com.cg.util.Roles;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginRepository loginRepo;
	
	Map<String, Login> authMap = new HashMap<>();
	
	
	@Override
	public Map<String, Login> getAuthMap() {
		return authMap;
	}

	@Override
	public String generateToken(Login login) {
		String token = encryptLogin(login);
		authMap.put(token, login);
		return token;
	}

	/*
	 * Method to encrypt password
	 */
	@Override
	public String encryptString(String str) {
		char[] arr = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		int ch;
		for (int idx = 0; idx < arr.length; idx++) {
			ch = arr[idx] + 3;
			sb.append((char) ch);
		}
		return sb.toString();
	}

	/*
	 * Method to decrypt password
	 */
	@Override
	public String decryptString(String str) {
		char[] arr = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		int ch;
		for (int idx = 0; idx < arr.length; idx++) {
			ch = arr[idx] - 3;
			sb.append((char) ch);
		}
		return sb.toString();
	}

	/*
	 * Method to generate encrypted token id
	 */
	@Override
	public String encryptLogin(Login loginAcnt) {
		return encryptString(loginAcnt.getUserId().toString()) + "-" + encryptString(loginAcnt.getRole());
	}
	
	@Override
	public boolean verifyLogin(String tokenId) throws LoginException {
		if (!authMap.containsKey(tokenId)) {
			throw new LoginException(FlightBookingConstants.INVALID_TOKEN);
		}
		return true;
	}
	
	/*
	 * Method for logging into an account
	 */
	
	@Override
	public Login doLogin(Integer userId, String password) throws LoginException{
		Optional<Login> optLogin = loginRepo.findById(userId);
		if(!optLogin.isPresent())
			throw new LoginException(FlightBookingConstants.USER_ID_NOT_FOUND);
		Login login = optLogin.get();
		if(!login.getPassword().contentEquals(encryptString(password))) {
			throw new LoginException(FlightBookingConstants.INCORRECT_PASSWORD);
		}
		return login;
	}
	
	@Override
	public boolean verifyRole(String tokenId) throws LoginException {
		String role= authMap.get(tokenId).getRole();
		if(role.equals(Roles.ADMIN))
			return true;
		throw new LoginException(FlightBookingConstants.ACEESS_DENIED);
	}

}
