package com.bookstore;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.bookstore.entities.Category;
import com.bookstore.exception.InternalServerException;
import com.bookstore.exception.NotFoundException;
import com.bookstore.rest.CategoryRestController;
import com.bookstore.services.ICategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryRestController.class)
public class CategoryRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ICategoryService iCategoryService;

	@Test
	public void testAddCategoryValid() throws Exception {
		Category cat = new Category("Thriller");
		when(iCategoryService.addCategory(cat)).thenReturn(cat);

		mockMvc.perform(post("/category/addCategory").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cat)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testAddCategoryInValid() throws Exception {
			Category cat = new Category("");
			when(iCategoryService.addCategory(cat)).thenThrow(InternalServerException.class);

			mockMvc.perform(post("/category/addCategory").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cat)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError()); // Internal Server 
	}
	
	@Test
	public void testAddCategoryInValid2() throws Exception {
			Category cat = new Category("");
			when(iCategoryService.addCategory(cat)).thenThrow(NotFoundException.class);

			mockMvc.perform(post("/category/addCategory").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cat)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()); // Internal Server 
	}
	
	@Test
	public void testEditCategoryValid() throws Exception {
		Category cat = new Category("Comedy");
		when(iCategoryService.editCategory(cat)).thenReturn(cat);

		mockMvc.perform(put("/category/editCategory").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cat)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testEditCategoryInValid() throws Exception {
		Category cat = new Category("");
		when(iCategoryService.editCategory(cat)).thenThrow(InternalServerException.class);

		mockMvc.perform(put("/category/editCategory").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cat)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError());
	}
	
	@Test
	public void testViewAllCategoriesValid() throws Exception {
		mockMvc.perform(get("/category/viewAllCategories").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void testViewAllCategoriesInValid() throws Exception {
		when(iCategoryService.viewAllCategories()).thenThrow(NotFoundException.class);
		mockMvc.perform(get("/category/viewAllCategories").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	
	@Test
	public void testDeleteCategoryValid() throws Exception {
		Category cat = new Category("Thriller");
		when(iCategoryService.removeCategory(cat)).thenReturn(cat);
		
		mockMvc.perform(delete("/category/deleteCategory").accept(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(cat)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}
	
	@Test void testDeleteCategoryInValid() throws Exception {
		Category cat = new Category("Romantic");
		when(iCategoryService.removeCategory(cat)).thenThrow(InternalServerException.class);
		
		mockMvc.perform(delete("/category/deleteCategory").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cat)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError());
	}
}
