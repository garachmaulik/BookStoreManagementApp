package com.bookstore.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entities.Customer;
import com.bookstore.exception.CustomerNotFoundException;
import com.bookstore.services.ICustomerService;

@CrossOrigin
@RestController
@RequestMapping(value = "/customer")
public class CustomerRestController {

	@Autowired
	private ICustomerService service;

	// URL: http://localhost:8880/add
	@PostMapping(value = "/add", consumes = "application/json")
	public Customer createCustomer(@RequestBody Customer customer) throws CustomerNotFoundException {
		return service.createCustomer(customer);
	}

	// URL: http://localhost:8880/viewCustomer?id=123
	@GetMapping(value = "/viewCustomer", produces = "application/json")
	public Customer viewCustomer(@RequestParam("id") int custId) throws CustomerNotFoundException {
		return service.viewCustomer(custId);
	}

	// URL: http://localhost:8880/list
	@GetMapping(value = "/list", produces = "application/json")
	public List<Customer> listCustomers() throws CustomerNotFoundException {
		return service.listCustomers();
	}

	// URL: http://localhost:8880/customer/deletecustomer
	@PostMapping(value = "/delete", produces = "application/json")
	public Customer deleteCustomer(@RequestParam("id") int customerId) throws CustomerNotFoundException {
		return service.deleteCustomer(customerId);
	}

	// URL: http://localhost:8880/customer/updatecustomer
	@PostMapping(value = "/update", consumes = "application/json")
	public Customer updateCustomer(@RequestBody Customer customer) throws CustomerNotFoundException {
		return service.updateCustomer(customer);
	}
	
	@GetMapping(value = "/findCustomer", produces = "application/json")
	public Customer findCust(@RequestParam("email") String email, @RequestParam("pass") String pass)
	{
		return service.findCustomer(email, pass);
	}

}
