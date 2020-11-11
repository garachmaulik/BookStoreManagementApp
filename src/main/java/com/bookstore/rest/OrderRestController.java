package com.bookstore.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entities.Book;
import com.bookstore.entities.Customer;
import com.bookstore.entities.OrderDetails;
import com.bookstore.exception.InternalServerException;
import com.bookstore.exception.NotFoundException;
import com.bookstore.services.IOrderService;

@RestController
@RequestMapping(value = "/order")
public class OrderRestController {
	
	@Autowired
	IOrderService iOrderService;
	
	// URL: http://localhost:8080/order/add
	@PostMapping(value = "/add", consumes = "application/json")   //runs in postman
	public OrderDetails addOrder(@RequestBody OrderDetails od) throws NotFoundException, InternalServerException {
		return iOrderService.addOrder(od);
	}
	
	// URL: http://localhost:8080/order/listAllOrders   // runs in postman
	@GetMapping(value = "/listAllOrders", produces = "application/json")
	public List<OrderDetails> listOrders() throws NotFoundException{
		return iOrderService.listAllOrders();
	}
	
	// URL: http://localhost:8080/order/updateOrder
	@PutMapping(value = "/updateOrder", consumes = "application/json")
	public OrderDetails update(@RequestBody OrderDetails od) throws NotFoundException, InternalServerException{
		return iOrderService.updateOrder(od);
	}
	
	// URL: http://localhost:8080/order/cancelOrder
	@DeleteMapping(value = "/cancelOrder", consumes = "application/json")
	public OrderDetails cancel(@RequestBody OrderDetails od) throws NotFoundException, InternalServerException {
		return iOrderService.cancelOrder(od);
	}
	
	// URL: http://localhost:8080/order/listOrderByCustomer
	@GetMapping(value = "/listOrderByCustomer", consumes = "application/json")
	public List<OrderDetails> listByCustomer(@RequestBody Customer cs) throws NotFoundException{
		return iOrderService.listOrderByCustomer(cs);
	}
	
	// URL: http://localhost:8080/order/viewOrderByBook
	@GetMapping(value = "/viewOrderByBook", consumes = "application/json")
	public List<OrderDetails> viewOrderByBook(@RequestBody Book book) throws NotFoundException{
		return iOrderService.viewOrderByBook(book);
	}
	
	// URL: http://localhost:8080/order/viewOrderForCustomer
	@GetMapping(value = "/viewOrderForCustomer", consumes = "application/json")
	public OrderDetails viewOrderForCustomer(@RequestBody Customer cs) throws NotFoundException{
		return iOrderService.viewOrderForCustomer(cs);
	}
	
	// URL: http://localhost:8080/order/viewOrderForAdmin
	@GetMapping(value = "/viewOrderForAdmin", consumes = "application/json")
	public OrderDetails viewOrderAdmin(@RequestBody OrderDetails od) throws NotFoundException {
		return iOrderService.viewOrderForAdmin(od);
	}

}
