package com.cg.service;

import com.cg.model.Login;

public interface LoginService {

	String encryptString(String str);

	String decryptString(String str);

	String encryptLogin(Login loginAcnt);

}
