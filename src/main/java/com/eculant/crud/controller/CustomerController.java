package com.eculant.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eculant.crud.model.Customer;
import com.eculant.crud.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model theModel){
		
		//get customers from Dao
		List<Customer> theCustomer = customerService.getCustomers();
		
		//add customers to model
		theModel.addAttribute("customers", theCustomer);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String addCustomer(Model theModel) {
		
		//model attribute to bind data
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustom(@ModelAttribute("customer") Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customers/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String ShowFormForUpdate(@RequestParam("id") int theId, Model theModel) {
		
		//get customer from service
		Customer theCustomer = customerService.getCustomer(theId);
		
		//get customer as model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("id") int theId) {
		
		//delete the customer
		customerService.deleteCustomer(theId);
		
		return "redirect:/customers/list";
	}
}
