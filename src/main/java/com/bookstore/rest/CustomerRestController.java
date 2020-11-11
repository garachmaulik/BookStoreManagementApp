package com.bookstore.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entities.Customer;
import com.bookstore.exception.CustomerNotFoundException;
import com.bookstore.services.ICustomerService;

@RestController
public class CustomerRestController {

	@Autowired
	private ICustomerService service;
	
	//URL: http://localhost:8880/add
	@PostMapping(value = "/add", consumes ="application/json") 
	public String addCustomer(@RequestBody Customer customer) throws CustomerNotFoundException {
		service.add(customer);
		return"Customer added to list";
	}
	//URL: http://localhost:8880/get?id=123
	@GetMapping(value="/get" ,produces ="application/json")
	public Customer getCustomer(@RequestParam("customer_Id") int customer_Id ) throws CustomerNotFoundException {
		return service.get(customer_Id);
	}
	//URL: http://localhost:8880/list 
	@GetMapping(value="/list" ,produces ="application/json")
	public List<Customer> listCustomers() throws CustomerNotFoundException{
		return service.list();
	}
	
	//URL: http://localhost:8880/Id/10
	@GetMapping(value="/customerId{Id}" ,produces ="application/json")
	public List<Customer> byId(@PathVariable Integer Customer_Id) throws CustomerNotFoundException{
		return service.customersById(Customer_Id);
	}
}
