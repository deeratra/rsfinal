package com.cts.foodster.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {
	
	
	//Redirect to login page after clicking on sign out
	@GetMapping(value="signOut.html")
	public ModelAndView logout(HttpSession httpSession){
		ModelAndView modelAndView= new ModelAndView();
		httpSession.removeAttribute("employee");
		httpSession.invalidate();
		modelAndView.setViewName("login");
		return modelAndView;
	}

}
