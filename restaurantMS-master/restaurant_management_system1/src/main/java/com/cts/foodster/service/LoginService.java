package com.cts.foodster.service;

import com.cts.foodster.bean.Login;

public interface LoginService {

	public int Authenticate(Login login);
	public String registerAdmin(Login login);
	public Login getUser(String id);
	public Login getProfile(Login login);
}
