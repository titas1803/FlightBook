package com.cg.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.exceptions.LoginException;
import com.cg.model.Login;
import com.cg.repository.LoginRepository;

public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginRepository loginRepo;
	
	Map<String, Login> authMap = new HashMap<>();
	
	
	
	public Map<String, Login> getAuthMap() {
		return authMap;
	}

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
	
	public boolean verifyLogin(String tokenId) throws LoginException {
		if (!authMap.containsKey(tokenId)) {
			throw new LoginException("Invalid Login token");
		}
		return true;
	}
	
	/*
	 * Method for logging into an account
	 */
	public Login doLogin(Integer userId, String password) throws LoginException{
		Optional<Login> optLogin = loginRepo.findById(userId);
		if(!optLogin.isPresent())
			throw new LoginException("User not Found");
		Login login = optLogin.get();
		if(!login.getPassword().contentEquals(encryptString(password))) {
			throw new LoginException("Password doesn't match");
		}
		return login;
	}

}
