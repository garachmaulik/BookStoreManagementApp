package com.bookstore;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bookstore.entities.Address;
import com.bookstore.entities.Customer;
import com.bookstore.exception.CustomerNotFoundException;
import com.bookstore.rest.CustomerRestController;
import com.bookstore.services.ICustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerRestController.class)
public class CustomerRestControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper object;
	
	@MockBean
	private ICustomerService service;
	
	@Test
	public void testCreateCustomerValid() throws Exception
	{
		Address add=new Address("Model town","delhi","India","171234");
		Customer customer= new Customer("abc@gmail.com","Manoj Kumar","abc@123",add,"Role","9876543210", LocalDate.now());
		Mockito.when(service.add(customer)).thenReturn(customer);
		
		mvc.perform(post("/add").accept(MediaType.APPLICATION_JSON).content(object.writeValueAsString(customer))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	@Test
	public void testCreateCustomerInvalid() throws Exception{
		Address add=new Address("123, Model town","delhi","India","171234");
		Customer customer= new Customer("abc@gmail.com","Manoj Kumar","abc@123",add,"Role","9876543210", LocalDate.now());	
		when(service.add(customer)).thenThrow(CustomerNotFoundException.class);
		
		mvc.perform(post("/Customer/addCustomer").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}	
	@Test
	public void testFindBycustomerIdValid() throws Exception {
		Address add=new Address("123, Model town","delhi","India","171234");
		Customer customer= new Customer("abc@gmail.com","Manoj Kumar","abc@123",add,"Role","9876543210", LocalDate.now());	
		when(service.get(11)).thenReturn(customer);
		mvc.perform(get("/get").accept(MediaType.APPLICATION_JSON).content("11")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	@Test
	public void testFindBycustomerIdInvalid() throws Exception {
		when(service.get(15)).thenThrow(CustomerNotFoundException.class);
		mvc.perform(get("/Customer/get").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	@Test
	public void testListAllCustomersValid() throws Exception {
		mvc.perform(post("/list").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	@Test
	public void testListAllCustomersInvalid() throws Exception {
		when(service.list()).thenThrow(CustomerNotFoundException.class);
		mvc.perform(get("/list").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	@Test
	public void testFindBycustomersByIdValid() throws Exception {
		mvc.perform(post("/Id").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	@Test
	public void testFindBycustomersByIdInvalid() throws Exception {
		when(service.customersById(10)).thenThrow(CustomerNotFoundException.class);
		mvc.perform(get("/Customer/Id").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	
	
}
