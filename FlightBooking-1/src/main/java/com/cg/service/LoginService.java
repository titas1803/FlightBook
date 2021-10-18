package com.cg.service;

import java.util.Map;

import com.cg.exceptions.LoginException;
import com.cg.model.Login;

public interface LoginService {

	String encryptString(String str);

	String decryptString(String str);

	String encryptLogin(Login loginAcnt);

	String generateToken(Login login);

	Login doLogin(Integer userId, String password) throws LoginException;

	Map<String, Login> getAuthMap();

	boolean verifyLogin(String tokenId) throws LoginException;

	boolean verifyRole(String tokenId) throws LoginException;

}
