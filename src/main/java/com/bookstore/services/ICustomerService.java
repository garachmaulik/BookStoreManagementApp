package com.bookstore.services;

import java.util.List;

import com.bookstore.entities.Customer;
import com.bookstore.exception.CustomerNotFoundException;

public interface ICustomerService {
			
	Customer createCustomer(Customer customer) throws CustomerNotFoundException;
	
	List<Customer> listCustomers() throws CustomerNotFoundException;
	
	Customer updateCustomer(Customer customer) throws CustomerNotFoundException;
	
	Customer deleteCustomer(int custId) throws CustomerNotFoundException;
	
	Customer viewCustomer(int custId) throws CustomerNotFoundException;
	
	Customer findCustomer(String email, String pass);
	
}
