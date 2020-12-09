package com.bookstore.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entities.Customer;
import com.bookstore.entities.User;
import com.bookstore.exception.CustomerNotFoundException;
import com.bookstore.exception.UserNotFoundException;
import com.bookstore.repositories.ICustomerRepository;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private ICustomerRepository iCustomerRepository;
	
	@Autowired
	private ILoginService iLoginService;
	
	@Override
	@Transactional
	public Customer createCustomer(Customer customer) throws CustomerNotFoundException {
		if(customer == null)
		{
			throw new CustomerNotFoundException("Customer is null");
		}
		iCustomerRepository.save(customer);
		User user = new User(customer.getEmail(), customer.getPassword(), "Customer");
		try {
			iLoginService.add(user);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
		return customer;
	}
	
	@Override
	public Customer viewCustomer(int custId) throws CustomerNotFoundException {
		if (!iCustomerRepository.existsById(custId)) {
			 new CustomerNotFoundException("Customer Id doesn't exist: "+ custId);
		}
		return iCustomerRepository.getOne(custId) ;
		
	}

	@Override
	public List<Customer> listCustomers()  throws CustomerNotFoundException{
		List<Customer> customer=new ArrayList<Customer>();
		customer=iCustomerRepository.findAll();
		if(customer.isEmpty()) {
			throw new CustomerNotFoundException("Customer List is empty");
		}
		return customer;
	}
	@Override
	public Customer deleteCustomer(int custId) throws CustomerNotFoundException {
		if(!iCustomerRepository.existsById(custId))
		{
			throw new CustomerNotFoundException("Customer is null");
		}
		Customer customer = iCustomerRepository.getOne(custId);
		iCustomerRepository.deleteById(custId);
		return customer;
	}
	
	@Override
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
		if(customer == null)
		{
			throw new CustomerNotFoundException("Customer not found");
		}
		return iCustomerRepository.saveAndFlush(customer);
	}

	@Override
	public Customer findCustomer(String email, String pass) {
		return iCustomerRepository.findByEmailAndPassword(email, pass);
	}

}
