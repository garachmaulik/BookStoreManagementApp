package com.bookstore.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entities.Book;
import com.bookstore.entities.Customer;
import com.bookstore.entities.OrderDetails;
import com.bookstore.exception.InternalServerException;
import com.bookstore.exception.NotFoundException;
import com.bookstore.repositories.IBookRepository;
import com.bookstore.repositories.ICustomerRepository;
import com.bookstore.repositories.IOrderRepository;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private IOrderRepository iOrderRepository;
	
	@Autowired
	private IBookRepository iBookRepository;
	
	@Autowired
	private ICustomerRepository iCustomerRepository;

	@Override
	@Transactional
	public OrderDetails addOrder(OrderDetails od) throws NotFoundException, InternalServerException {
		if(od == null) {
			throw new InternalServerException("Order is null");
		}
		od.setBook(iBookRepository.getOne(od.getBook().getBookId()));
		od.getBookOrder().setCustomer(iCustomerRepository.getOne(od.getBookOrder().getCustomer().getCustomerId()));
		od.getBookOrder().setShippingAddress(od.getBookOrder().getCustomer().getAddress());
		return iOrderRepository.save(od);
	}

	@Override
	public OrderDetails cancelOrder(OrderDetails od) throws NotFoundException {
		if(od == null || !iOrderRepository.existsById(od.getId())) {
			throw new NotFoundException("Order does not exist");
		}
		iOrderRepository.delete(od);
		return od;
	}

	@Override
	public List<OrderDetails> listAllOrders() throws NotFoundException {
		List<OrderDetails> orderList = new ArrayList<OrderDetails>();
		orderList = iOrderRepository.findAll();
		if(orderList == null || orderList.isEmpty()) {
			throw new NotFoundException("Order List is empty");
		}
		return orderList;
	}

	@Override
	public List<OrderDetails> listOrderByCustomer(int custId) throws NotFoundException { // change it to listOrderByCustomer(int CustId)  
		return iOrderRepository.listByCustomer(custId);
	}

	@Override
	public OrderDetails updateOrder(OrderDetails od) throws NotFoundException {
		if(od == null || !(iOrderRepository.existsById(od.getId()))) {
			throw new NotFoundException("Order does not exist in the records.");
		}
		return iOrderRepository.saveAndFlush(od);
	}

	@Override
	public List<OrderDetails> viewOrderByBook(Book book) throws NotFoundException {
		if(book == null) {
			throw new NotFoundException("Cannot find orders because book is null.");
		}
		if(iOrderRepository.findByBook(book).isEmpty()) {
			throw new NotFoundException("No Order exists with book : " + book);
		}
		return iOrderRepository.findByBook(book);
	}

	@Override
	public OrderDetails viewOrderForAdmin(OrderDetails od) throws NotFoundException {
		/*
		 * if(od == null) { throw new NotFoundException("Order Not Found"); }
		 * if(!od.getBookOrder().getCustomer().getRole().equalsIgnoreCase("admin")) {
		 * throw new NotFoundException("Customer is not an admin."); } return od;
		 */
		return null;
	}

	@Override
	public OrderDetails viewOrderForCustomer(Customer cs) throws NotFoundException {
		/*
		 * if(cs == null) { throw new NotFoundException("Customer Not Found"); }
		 * if(!cs.getRole().equalsIgnoreCase("customer")) { throw new
		 * NotFoundException("Customer not found"); } OrderDetails od =
		 * listAllOrders().get(0);
		 */
		return null;
	}

}
