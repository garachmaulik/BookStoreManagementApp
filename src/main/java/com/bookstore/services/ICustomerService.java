package com.bookstore.services;

import java.util.List;

import com.bookstore.entities.Customer;
import com.bookstore.exception.CustomerNotFoundException;

public interface ICustomerService {
			
	Customer add(Customer customer) throws CustomerNotFoundException;
	
	Customer get(int id) throws CustomerNotFoundException;
	
	List<Customer> list() throws CustomerNotFoundException;
	
	List<Customer> customersById(Integer Id) throws CustomerNotFoundException;
	
}
