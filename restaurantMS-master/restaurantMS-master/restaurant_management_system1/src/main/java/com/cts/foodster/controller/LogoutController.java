package com.cts.foodster.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class LogoutController {
	
	
	//Redirect to login page after clicking on sign out
	@RequestMapping(value="signOut.html")
	public ModelAndView logout(HttpSession httpSession){
		ModelAndView modelAndView= new ModelAndView();
		httpSession.removeAttribute("employee");
		httpSession.invalidate();
		modelAndView.setViewName("login");
		return modelAndView;
	}

}
