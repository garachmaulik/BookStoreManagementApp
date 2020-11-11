package com.bookstore.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entities.Customer;
import com.bookstore.exception.CustomerNotFoundException;
import com.bookstore.repositories.ICustomerRepository;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private ICustomerRepository iCustomerRepository;
	
	@Override
	public Customer add(Customer customer) throws CustomerNotFoundException {
		if(customer == null)
		{
			throw new CustomerNotFoundException("Customer is null");
		}
		iCustomerRepository.save(customer);
		return customer;
	}
	
	@Override
	public Customer get(int customerId) throws CustomerNotFoundException {
		 return iCustomerRepository.findById(customerId).orElseThrow(()-> 
		 new CustomerNotFoundException("Customer Id doesn't exist: "+customerId));
		
	}

	@Override
	public List<Customer> list()  throws CustomerNotFoundException{
		List<Customer> customer=new ArrayList<Customer>();
		customer=iCustomerRepository.findAll();
		if(customer.isEmpty()) {
			throw new CustomerNotFoundException("Customer List is empty");
		}
		return customer;
	}

	@Override
	public List<Customer> customersById(Integer Customer_Id) throws CustomerNotFoundException {
		List<Customer> customer=new ArrayList<Customer>();
		customer=iCustomerRepository.findByCustomer_Id(Customer_Id);
		if(customer.isEmpty()) {
			throw new CustomerNotFoundException("Customer List is empty");
		}
		return customer;
	}

}
