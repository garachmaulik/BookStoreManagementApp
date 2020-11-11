package com.bookstore.services;

import java.util.List;

import com.bookstore.entities.Book;
import com.bookstore.entities.Customer;
import com.bookstore.entities.OrderDetails;
import com.bookstore.exception.InternalServerException;
import com.bookstore.exception.NotFoundException;

public interface IOrderService {

	public OrderDetails addOrder(OrderDetails od) throws InternalServerException, NotFoundException;

	public OrderDetails cancelOrder(OrderDetails od) throws NotFoundException, InternalServerException;

	public List<OrderDetails> listAllOrders() throws NotFoundException;

	public List<OrderDetails> listOrderByCustomer(Customer cs) throws NotFoundException; // Dont change

	public OrderDetails updateOrder(OrderDetails od) throws NotFoundException, InternalServerException;

	public List<OrderDetails> viewOrderByBook(Book book) throws NotFoundException;

	public OrderDetails viewOrderForAdmin(OrderDetails od) throws NotFoundException;

	public OrderDetails viewOrderForCustomer(Customer cs) throws NotFoundException;

}
