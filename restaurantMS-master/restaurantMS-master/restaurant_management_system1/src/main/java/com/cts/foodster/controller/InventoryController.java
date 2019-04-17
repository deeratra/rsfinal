package com.cts.foodster.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cts.foodster.bean.Inventory;
import com.cts.foodster.service.InventoryService;

@Controller
public class InventoryController {

	@Autowired
	InventoryService inventoryService;
	
	@RequestMapping(value="addInventory.html")
	public String getAddInventory(){
		return "addInventory";
	}
	
	//Redirect to viewInventory page
	@GetMapping(value="viewInventory")
	public ModelAndView getViewInventory(){
		ModelAndView modelAndView= new ModelAndView();
		if(inventoryService.getAllInventory()!=null){
		modelAndView.addObject("inventorys",inventoryService.getAllInventory());
		modelAndView.setViewName("viewInventory");}
		
		else
		{
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}
	
	
	//Add an inventory in the database
	@RequestMapping(value="addInventory.html",method=RequestMethod.POST)
	public ModelAndView addInventory(@ModelAttribute Inventory inventory){
		ModelAndView modelAndView = new ModelAndView();
		String s=inventoryService.addInventory(inventory);
		if("success".equals(s))
		{	String info = "New Inventory Added";
			modelAndView.addObject("info",info);
			modelAndView.setViewName("viewInventory");
			modelAndView.addObject("inventorys",inventoryService.getAllInventory());
		}else{
		//	String info = "Inventory Id already exists.";
	//	modelAndView.addObject("info",info);
		modelAndView.setViewName("error");
		}
			
		return modelAndView;
	}
	
	//Delete a particular inventory
	@GetMapping(value="deleteInventory.html")
	public ModelAndView deleteInventory(@RequestParam String id){
		ModelAndView modelAndView= new ModelAndView();
		Inventory inventory= inventoryService.getInventory(id);
		if("success".equals(inventoryService.deleteInventory(inventory))){
			//String info = "Inventory Deleted";
			//modelAndView.addObject("info",info);
			modelAndView.addObject("inventorys",inventoryService.getAllInventory());
			modelAndView.setViewName("viewInventory");
		}else{
			//modelAndView.addObject("inventorys",inventoryService.getAllInventory());
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}
	
	
	//Search a particular inventory
	@PostMapping(value="searchInventory.html")
	public ModelAndView searchInventory(@RequestParam String name){
		ModelAndView modelAndView = new ModelAndView();
		if(inventoryService.searchInventory(name)!=null){
		modelAndView.addObject("inventorys",inventoryService.searchInventory(name));
		modelAndView.setViewName("viewInventory");}
		else
		{
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}
	
	//Sort the inventory list
	@GetMapping(value="sortInventory")
	public ModelAndView sortInventory(@RequestParam String order){
		ModelAndView modelAndView = new ModelAndView();
		
		if(inventoryService.sortInventory(order)!=null){
		modelAndView.addObject("inventorys",inventoryService.sortInventory(order));
		modelAndView.setViewName("viewInventory");}
		
		else
		{
			modelAndView.setViewName("error");
		}
		return modelAndView;
	}
	
	//Redirect to edit inventory page for updating the inventory details
	@GetMapping(value="editInventory")
	public ModelAndView getEditInventory(@RequestParam String id){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("id",id);
		modelAndView.setViewName("editInventory");
		return modelAndView;
	}
	
	//Redirect to viewInventory Page after editing of a particular inventory
	@PostMapping(value="editInventory")
	public ModelAndView editInventory(@ModelAttribute Inventory inventory){
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(inventory);
		if("success".equals(inventoryService.editInventory(inventory))){
			String info = "Inventory Edit Successfull";
			modelAndView.addObject("info",info);
			modelAndView.addObject("inventorys",inventoryService.getAllInventory());
			modelAndView.setViewName("viewInventory");
		}else{
		//	modelAndView.addObject("inventorys",inventoryService.getAllInventory());
			modelAndView.setViewName("error");
		}return modelAndView;
	}
}
