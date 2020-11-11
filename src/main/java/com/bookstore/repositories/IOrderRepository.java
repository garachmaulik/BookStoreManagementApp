package com.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.entities.Book;
import com.bookstore.entities.OrderDetails;

@Repository
public interface IOrderRepository extends JpaRepository<OrderDetails, Integer> {

	@Query("FROM OrderDetails od WHERE od.bookOrder.orderId IN (FROM BookOrder bo where bo.customer.customerId=:id)")
	List<OrderDetails> listByCustomer(@Param("id") int csId);

	List<OrderDetails> findByBook(Book book);

}
