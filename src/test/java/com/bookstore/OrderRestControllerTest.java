package com.bookstore;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bookstore.entities.Book;
import com.bookstore.entities.Customer;
import com.bookstore.entities.OrderDetails;
import com.bookstore.exception.InternalServerException;
import com.bookstore.exception.NotFoundException;
import com.bookstore.rest.OrderRestController;
import com.bookstore.services.IOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderRestController.class)
public class OrderRestControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	IOrderService iOrderService;

	@Test
	public void testAddOrderValid() throws Exception {
		OrderDetails od = new OrderDetails();
		when(iOrderService.addOrder(od)).thenReturn(od);

		mockMvc.perform(post("/order/add").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(od)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testAddOrderInValid() throws Exception {
		OrderDetails od = new OrderDetails();
		when(iOrderService.addOrder(od)).thenThrow(InternalServerException.class);

		mockMvc.perform(post("/order/add").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(od)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError());
	}

	@Test
	public void testListAllOrdersValid() throws Exception {
		mockMvc.perform(get("/order/listAllOrders").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testListAllOrdersInValid() throws Exception {
		when(iOrderService.listAllOrders()).thenThrow(NotFoundException.class);
		mockMvc.perform(get("/order/listAllOrders").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testUpdateOrderValid() throws Exception {
		OrderDetails od = new OrderDetails();
		od.setId(99);
		when(iOrderService.updateOrder(od)).thenReturn(od);

		mockMvc.perform(put("/order/updateOrder").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(od)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testUpdateOrderInValid() throws Exception {
		OrderDetails od = new OrderDetails();
		od.setId(99);
		when(iOrderService.updateOrder(od)).thenThrow(InternalServerException.class);

		mockMvc.perform(put("/order/updateOrder").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(od)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError());
	}

	@Test
	public void testCancelOrderValid() throws Exception {
		OrderDetails od = new OrderDetails();
		od.setId(99);
		when(iOrderService.cancelOrder(od)).thenReturn(od);

		mockMvc.perform(delete("/order/cancelOrder").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(od)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testCancelOrderInValid() throws Exception {
		OrderDetails od = new OrderDetails();
		od.setId(99);
		when(iOrderService.cancelOrder(od)).thenThrow(InternalServerException.class);

		mockMvc.perform(delete("/order/cancelOrder").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(od)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError());
	}

	@Test
	public void testListOrderByCustomerValid() throws Exception {
		Customer cs = new Customer();
		cs.setCustomerId(103);
		List<OrderDetails> odList = iOrderService.listOrderByCustomer(cs);
		when(iOrderService.listOrderByCustomer(cs)).thenReturn(odList);

		mockMvc.perform(get("/order/listOrderByCustomer").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cs)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testListOrderByCustomerInValid() throws Exception {
		Customer cs = new Customer();
		cs.setCustomerId(103);
		when(iOrderService.listOrderByCustomer(cs)).thenThrow(NotFoundException.class);

		mockMvc.perform(get("/order/listOrderByCustomer").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cs)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testViewOrderForCustomerValid() throws Exception {
		Customer cs = new Customer();
		cs.setCustomerId(103);
		OrderDetails od = new OrderDetails();
		when(iOrderService.viewOrderForCustomer(cs)).thenReturn(od);

		mockMvc.perform(get("/order/viewOrderForCustomer").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cs)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testViewOrderForCustomerInValid() throws Exception {
		Customer cs = new Customer();
		cs.setCustomerId(103);
		when(iOrderService.viewOrderForCustomer(cs)).thenThrow(NotFoundException.class);

		mockMvc.perform(get("/order/viewOrderForCustomer").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cs)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testViewOrderByBookValid() throws Exception {
		Book book = new Book();
		book.setBookId(1);
		List<OrderDetails> odList = new ArrayList<OrderDetails>();
		when(iOrderService.viewOrderByBook(book)).thenReturn(odList);

		mockMvc.perform(get("/order/viewOrderByBook").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(book)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testViewOrderByBookInValid() throws Exception {
		Book book = new Book();
		book.setBookId(1);
		when(iOrderService.viewOrderByBook(book)).thenThrow(NotFoundException.class);

		mockMvc.perform(get("/order/viewOrderByBook").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(book)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testViewOrderForAdminValid() throws Exception {

		OrderDetails od = new OrderDetails();
		when(iOrderService.viewOrderForAdmin(od)).thenReturn(od);

		mockMvc.perform(get("/order/viewOrderForAdmin").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(od)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}
	
	@Test
	public void testViewOrderForAdminInValid() throws Exception {

		OrderDetails od = new OrderDetails();
		when(iOrderService.viewOrderForAdmin(od)).thenThrow(NotFoundException.class);

		mockMvc.perform(get("/order/viewOrderForAdmin").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(od)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

	}

}
