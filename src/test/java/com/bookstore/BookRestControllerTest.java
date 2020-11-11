package com.bookstore;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.bookstore.entities.Book;
import com.bookstore.entities.Category;
import com.bookstore.exception.BookNotFoundException;
import com.bookstore.rest.BookRestController;
import com.bookstore.services.IBookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(BookRestController.class)
public class BookRestControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper object;	
	
	@MockBean
	private IBookService service;
	
	@Test
	public void testCreateBookValid() throws Exception
	{		
		Book book = new Book("Harry Potter", "J K Rowling", new Category(), "Book about magic", "1234", 120.0, LocalDate.now(), LocalDate.now());
		Mockito.when(service.createBook(book)).thenReturn(book);		
		
		mvc.perform(post("/book/add").accept(MediaType.APPLICATION_JSON).content(object.writeValueAsString(book))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void testCreateBookInValid() throws Exception{
		Book book = new Book("Harry Potter", "J K Rowling", new Category(), "Book about magic", "1234", 120.0, LocalDate.now(), LocalDate.now());
		when(service.createBook(book)).thenThrow(BookNotFoundException.class);		
		
		mvc.perform(post("/Book/createBook").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	
	@Test
	public void testListAllBooksValid() throws Exception{		
		mvc.perform(get("/book/list").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void testListAllBooksInValid() throws Exception {
		when(service.listAllBooks()).thenThrow(BookNotFoundException.class);
		
		mvc.perform(get("/book/list").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	
	@Test
	public void testDeleteBookValid() throws Exception{
		Book b=new Book();
		b.setBookId(1);
		when(service.deleteBook(b)).thenReturn(b);
		
		mvc.perform(delete("/book/delete").accept(MediaType.APPLICATION_JSON).content(object.writeValueAsString(b))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteBookInValid() throws Exception{
		Book book = new Book();
		book.setBookId(1);
		when(service.deleteBook(book)).thenThrow(BookNotFoundException.class);		
		
		mvc.perform(delete("/Book/deleteBook").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	
	@Test
	public void testEditBookValid() throws Exception{
		Book book= new Book();
		book.setBookId(1);
		when(service.editBook(book)).thenReturn(book);
		
		mvc.perform(post("/Book/editBook").accept(MediaType.APPLICATION_JSON).content(object.writeValueAsString(book))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void testEditBookInValid() throws Exception{
		Book book= new Book();
		book.setBookId(1);
		when(service.editBook(book)).thenThrow(BookNotFoundException.class);
		
		mvc.perform(post("/Book/editBook").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
		
	@Test
	public void testViewBookValid() throws Exception{
		Book book= new Book();
		book.setBookId(1);
		when(service.viewBook(book)).thenReturn(book);
		
		mvc.perform(get("/book/view").content(object.writeValueAsString(book))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
		
	@Test
	public void testViewBookInValid() throws Exception{ 
		Book book= new Book();
		book.setBookId(1);
		when(service.viewBook(book)).thenThrow(BookNotFoundException.class);
		
		mvc.perform(get("/Book/viewBook").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	
	/* ############################################### */
	@Test
	public void testFindByTitleValid() throws Exception{
		Book book=new Book();
		book.setTitle("Harry Potter");
		when(service.findByTitle("Harry Potter")).thenReturn(book);
		
		mvc.perform(get("/Book/viewBook").accept(MediaType.APPLICATION_JSON).content(object.writeValueAsString(book))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	/* ############################################### */
	
	@Test
	public void testFindByTitleInValid() throws Exception{
		when(service.findByTitle("Harry Potter")).thenThrow(BookNotFoundException.class);
		
		mvc.perform(get("/Book/viewBook").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
	@Test
	public void testSearchValid() throws Exception{
		List<Book> book= new ArrayList<Book>();
		when(service.search("magic")).thenReturn(book);
		
		mvc.perform(get("/book/keyword/magic")).andExpect(status().isOk());
	}
	/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
	
	@Test
	public void testSearchInValid() throws Exception{
		when(service.search("magic")).thenThrow(BookNotFoundException.class);
		
		mvc.perform(get("/Book/viewBook").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	
	@Test
	public void testListBooksByCategoryValid() throws Exception{
		List<Book> books=new ArrayList<Book>();
		
		when(service.listBooksByCategory("Novel")).thenReturn(books);
		
		mvc.perform(get("/book/category").content("Novel")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	/************************************************/
	@Test
	public void testListBooksByCategoryInValid() throws Exception{
		when(service.listBooksByCategory("")).thenThrow(BookNotFoundException.class);
		
		mvc.perform(get("/book/category").content("")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	/************************************************/
	
	@Test
	public void testBestSellingBooksValid() throws Exception{		
		mvc.perform(get("/book/listbooks").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	/******************************************/
	@Test
	public void testBestSellingBooksInValid() throws Exception {
		when(service.listAllBooks()).thenThrow(BookNotFoundException.class);		
		mvc.perform(get("/book/listbooks").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	/*************************************************/
}
	
	
	
