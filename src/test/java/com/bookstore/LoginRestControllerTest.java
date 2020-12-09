package com.bookstore;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bookstore.entities.User;
import com.bookstore.exception.UserNotFoundException;
import com.bookstore.rest.LoginRestController;
import com.bookstore.services.ILoginService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(LoginRestController.class)
public class LoginRestControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper object;
	
	@MockBean
	private ILoginService service;
	
	@Test
	public void testAddUserValid() throws Exception
	{
		User user=new User("abc@gmail.com","abc@123","Admin");
		Mockito.when(service.add(user)).thenReturn(user);
		
		mvc.perform(post("/add").accept(MediaType.APPLICATION_JSON).content(object.writeValueAsString(user))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	public void testAddUserInvalid() throws Exception{
		User user=new User("abc@gmail.com","abc@123","Admin");
		when(service.add(user)).thenThrow(UserNotFoundException.class);
		
		mvc.perform(post("/Customer/addCustomer").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is5xxServerError());
	}	
	
//	public void testDeleteUserValid() throws Exception{
//		User user=new User("abc@gmail.com","abc@123","Admin");
//		when(service.delete(user)).thenReturn(user);
//		
//		mvc.perform(post("/User/deleteuser").accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
//	}
//	
//	public void testDeleteUserInvalid() throws Exception{
//		User user=new User("abcd@gmail.com","abc@123","Admin");
//		when(service.delete(user)).thenReturn(user);
//		
//		mvc.perform(post("/User/deleteuser").accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
//	}
	
//	public void testUservalidateValid() throws Exception{
//		User user=new User("abc@gmail.com","abc@123","Admin");
//		when(service.validate("1","abc@123")).thenReturn(user);
//
//		mvc.perform(post("/User/validate").accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
//	}
	public void testUservalidateInvalid() throws Exception{
		when(service.validate("1","abcd@123")).thenThrow(UserNotFoundException.class);

		mvc.perform(post("/User/validate").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
}
}