package com.bookstore.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.entities.Customer;
import com.bookstore.exception.CustomerNotFoundException;

import java.util.List;
import java.util.Optional;
	
@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer>{
		
	@Query("FROM Customer WHERE customerId=:id")
	List<Customer> findByCustomer_Id(@Param("id")Integer Customer_Id)  throws CustomerNotFoundException;
	
	Customer findByEmailAndPassword(String email, String pass);
		
}
