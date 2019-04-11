package com.cts.foodster.dao;

import com.cts.foodster.bean.Login;

public interface LoginDAO {

	public Login Authenticate(Login login); //Used for authenticating user
	public String registerAdmin(Login login); //Used for registering the admin details
	public Login getUser(String id);  //Used for getting a particular user
}
