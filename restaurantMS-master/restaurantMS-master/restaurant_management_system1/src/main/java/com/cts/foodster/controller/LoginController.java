package com.cts.foodster.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cts.foodster.bean.Inventory;
import com.cts.foodster.bean.Login;
import com.cts.foodster.bean.Staff;
import com.cts.foodster.service.InventoryService;
import com.cts.foodster.service.LoginService;
import com.cts.foodster.service.StaffService;

@Controller
public class LoginController {
	@Autowired
	LoginService loginService;
	
	@Autowired
	InventoryService inventoryService;
	@Autowired
	StaffService staffService;
	
	@RequestMapping("/")
	public String AdminLogin(){
		return "login";
	}
	
	//Redirect to addInventory page after successful authentication of Id and Password
	@RequestMapping(value="login.html",method=RequestMethod.POST)
	public ModelAndView Login(@ModelAttribute Login login, HttpSession httpSession){
		ModelAndView mav = new ModelAndView();
		int a =loginService.Authenticate(login);
		if(a == 1){
			mav.setViewName("addInventory");
			Login login1 = loginService.getProfile(login);
			
			httpSession.setAttribute("employee", login1);
			
		}
		else {
			String info = "Wrong Credentials";
			httpSession.setAttribute("loginError", info);
			mav.addObject("info",info);
			mav.setViewName("login");
		}
		
		return mav;
		}
			
	
	
	@RequestMapping(value="login.html")
	public String getLogin(HttpSession httpSession){
		httpSession.removeAttribute("loginError");
		return "login";
	}
	
	//Redirect to login page after successful registration of admin details
	@PostMapping(value="registration.html")
	public ModelAndView register(@ModelAttribute Login login)
	{System.out.println("yes");
		ModelAndView modelAndView = new ModelAndView();
		String ans = loginService.registerAdmin(login);
		if("yes".equals(ans))
		{
			String info = new String("Details Added Successfully");
			modelAndView.addObject("loginError",info);
			//modelAndView
			modelAndView.setViewName("login");
			return modelAndView;
		}
		else if ("no".equals(ans))
		{
		String info = new String("User Id already exists");
		modelAndView.addObject("loginError",info);
			modelAndView.setViewName("login");
		return modelAndView;
		}
		else{
			modelAndView.setViewName("error");
			return modelAndView;
		}
	}
	
	
}
