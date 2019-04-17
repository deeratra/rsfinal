package com.cts.foodster.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.ModelAndView;

import com.cts.foodster.bean.Staff;
import com.cts.foodster.service.StaffService;

@Controller
public class StaffController {

	@Autowired
	StaffService staffService;
	
	@RequestMapping(value="addStaff.html")
	public String getAddStaff(){
		return "addStaff";
	}
	
	//Redirect to viewStaff after successfully registering the staff details
	@RequestMapping(value="addStaff.html",method=RequestMethod.POST)
	public ModelAndView addStaff(@ModelAttribute Staff staff){
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(staff);
		String s=staffService.addStaff(staff);
		if("success".equals(s))
		{
			modelAndView.addObject("staffs",staffService.getAllStaff());
			modelAndView.setViewName("viewStaff");
		}else{
			//modelAndView.addObject("staffs",staffService.getAllStaff());
		modelAndView.setViewName("error");}
		return modelAndView;
	}
	
	//Redirect to viewStaff page and displaying the list of all staffs
	@RequestMapping(value="viewStaff.html")
	public ModelAndView getViewStaff(){
		ModelAndView modelAndView= new ModelAndView();
		List<Staff> staffs=staffService.getAllStaff();
		
		if(staffs!=null){
		modelAndView.setViewName("viewStaff");
		modelAndView.addObject("staffs",staffs);
		}
		else
			modelAndView.setViewName("error");
		return modelAndView;
	}
	
	
	//Delete a particular staff by using the id of staff
	@GetMapping(value="deleteStaff.html")
	public ModelAndView deleteStaff(@RequestParam String id){
		ModelAndView modelAndView= new ModelAndView();
		Staff staff=staffService.getStaff(id);
		if("success".equals(staffService.deleteStaff(staff))){
			String info = "Staff Deleted";
			modelAndView.addObject("info",info);
			modelAndView.addObject("staffs",staffService.getAllStaff());
			modelAndView.setViewName("viewStaff");
		}else{
			//modelAndView.addObject("staffs",staffService.getAllStaff());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}
	
	
	//search a particular staff by his name
	@PostMapping(value="searchStaff.html")
	public ModelAndView searchStaff(@RequestParam String name){
		ModelAndView modelAndView = new ModelAndView();
		if(staffService.searchStaff(name)!=null){
		modelAndView.addObject("staffs",staffService.searchStaff(name));
		modelAndView.setViewName("viewStaff");
		}
		else{
		modelAndView.setViewName("error");
		}
		return modelAndView;
	}
	
	//Redirect to edit staff page for updating the staff details
	@GetMapping(value="editStaff.html")
	public ModelAndView getEditStaff(@RequestParam String id){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("id",id);
		modelAndView.setViewName("editStaff");
		return modelAndView;
	}
	
	
	//Redirect to viewStaff Page after editing of a particular staff
	@PostMapping(value="editStaff.html")
	public ModelAndView editStaff(@ModelAttribute Staff staff){
		ModelAndView modelAndView = new ModelAndView();
		if("success".equals(staffService.editStaff(staff))){
			modelAndView.addObject("staffs",staffService.getAllStaff());
			modelAndView.setViewName("viewStaff");
		}else{
		//	modelAndView.addObject("staffs",staffService.getAllStaff());
			modelAndView.setViewName("error");
		}return modelAndView;
	}
	}
