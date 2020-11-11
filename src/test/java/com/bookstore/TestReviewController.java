package com.bookstore;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.bookstore.entities.Book;
import com.bookstore.entities.Customer;
import com.bookstore.entities.Review;
import com.bookstore.exception.ReviewNotFoundException;
import com.bookstore.rest.ReviewRestController;
import com.bookstore.services.IReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ReviewRestController.class)
public class TestReviewController {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private IReviewService iReviewService;
	private ObjectMapper objectMapper = new ObjectMapper();
	//private Review review = new Review();

//	----------------------Add Review Valid----------------------
	
	@Test
	public void testAddReviewValid() throws Exception {

		Review review = new Review();
		Mockito.when(iReviewService.addReview(review)).thenReturn(review);
		review.setReviewId(1);
		mvc.perform(post("/review/add").contentType("application/json").content(objectMapper.writeValueAsString(review)))
				.andExpect(status().isOk());
	}

//	----------------------Add review Invalid----------------------	
	@Test
	public void testAddReviewInvalid() throws Exception {

		Review review = new Review();
		review.setReviewId(1);
		Mockito.when(iReviewService.addReview(review)).thenThrow(ReviewNotFoundException.class);
		mvc.perform(post("/review/add").contentType("application/json").content(objectMapper.writeValueAsString(review)))
				.andExpect(status().is(404));

	}

//	----------------------View Review Valid----------------------
	@Test
	public void testViewReviewValid() throws Exception {

		Review review = new Review();
		Mockito.when(iReviewService.viewReview(review)).thenReturn(review);
		review.setReviewId(1);
		mvc.perform(post("/review/view").contentType("application/json").content(objectMapper.writeValueAsString(review)))
				.andExpect(status().isOk());
	}

//	----------------------View review Invalid----------------------	
	@Test
	public void testViewReviewInvalid() throws Exception {

		Review review = new Review();
		review.setReviewId(1);
		Mockito.when(iReviewService.viewReview(review)).thenThrow(ReviewNotFoundException.class);
		mvc.perform(post("/review/view").contentType("application/json").content(objectMapper.writeValueAsString(review)))
				.andExpect(status().is(404));

	}

//	----------------------Update Review Valid----------------------
	@Test
	public void testUpdateReviewValid() throws Exception {

		Review review = new Review();
		Mockito.when(iReviewService.updateReview(review)).thenReturn(review);
		review.setReviewId(1);
		mvc.perform(put("/review/update").contentType("application/json").content(objectMapper.writeValueAsString(review)))
				.andExpect(status().isOk());
	}

//	----------------------Update review Invalid----------------------	
	@Test
	public void testUpdateReviewInvalid() throws Exception {

		Review review = new Review();
		review.setReviewId(1);
		Mockito.when(iReviewService.updateReview(review)).thenThrow(ReviewNotFoundException.class);
		mvc.perform(put("/review/update").contentType("application/json").content(objectMapper.writeValueAsString(review)))
				.andExpect(status().is(404));

	}

//	----------------------Delete Review Valid----------------------
	@Test
	public void testDeleteReviewValid() throws Exception {
		Review review = new Review();

		Mockito.when(iReviewService.deleteReview(review)).thenReturn(review);
		review.setReviewId(1);
		mvc.perform(delete("/review/delete").contentType("application/json").content(objectMapper.writeValueAsString(review)))
				.andExpect(status().isOk());
	}

//	----------------------Delete review Invalid----------------------	
	@Test
	public void testDeleteReviewInvalid() throws Exception {

		Review review = new Review();
		review.setReviewId(1);
		Mockito.when(iReviewService.deleteReview(review)).thenThrow(ReviewNotFoundException.class);
		mvc.perform(delete("/review/delete").contentType("application/json").content(objectMapper.writeValueAsString(review)))
				.andExpect(status().is(404));

	}

//	----------------------List Review Valid----------------------
	@Test
	public void testListReviewValid() throws Exception {

		Review review = new Review();
		ArrayList<Review> list = new ArrayList<>();
		list.add(review);
		Mockito.when(iReviewService.listAllReviews()).thenReturn(list);
		review.setReviewId(1);
		mvc.perform(get("/review/list").contentType("application/json")).andExpect(status().isOk());
	}

//	----------------------List review Invalid----------------------	
	@Test
	public void testListReviewInvalid() throws Exception {

		Review review = new Review();
		review.setReviewId(1);
		Mockito.when(iReviewService.listAllReviews()).thenThrow(ReviewNotFoundException.class);
		mvc.perform(get("/review/list").contentType("application/json")).andExpect(status().is(404));

	}
	
//	----------------------List Review By Book Valid----------------------
	@Test
	public void testListReviewByBookValid() throws Exception {

		Review review = new Review();
		Book book = new Book();
		ArrayList<Review> list = new ArrayList<>();
		list.add(review);
		Mockito.when(iReviewService.listAllReviewsByBook(book)).thenReturn(list);
		review.setReviewId(1);
		mvc.perform(get("/review/book").accept("application/json").content(objectMapper.writeValueAsString(book)).contentType("application/json")).andExpect(status().isOk());
	}

//	----------------------List Review By Book Invalid----------------------	
	@Test
	public void testListReviewByBookInvalid() throws Exception {

		Review review = new Review();
		Book book = new Book();

		Mockito.when(iReviewService.listAllReviewsByBook(book)).thenThrow(ReviewNotFoundException.class);
		mvc.perform(get("/review/book").contentType("application/json").content(objectMapper.writeValueAsString(review)))
				.andExpect(status().is(404));

	}
	
//	----------------------List Review By Customer Valid----------------------
	@Test
	public void testListReviewByCustomerValid() throws Exception {

		Review review = new Review();
		Customer c = new Customer();
		ArrayList<Review> list = new ArrayList<>();
		list.add(review);
		Mockito.when(iReviewService.listAllReviewsByCustomer(c)).thenReturn(list);
		review.setReviewId(1);
		mvc.perform(get("/review/customer").accept("application/json").content(objectMapper.writeValueAsString(c)).contentType("application/json")).andExpect(status().isOk());
	}

//	----------------------List Review By Customer Invalid----------------------	
	@Test
	public void testListReviewByCustomerInvalid() throws Exception {

		Review review = new Review();
		Customer c = new Customer();
		
		Mockito.when(iReviewService.listAllReviewsByCustomer(c)).thenThrow(ReviewNotFoundException.class);
		mvc.perform(get("/review/customer").contentType("application/json").content(objectMapper.writeValueAsString(review)))
				.andExpect(status().is(404));

	}
	
//	----------------------List Review By Most Favored Books Valid----------------------
	@Test
	public void testListReviewByMostFavoredBooksValid() throws Exception {

		Book book = new Book();
		ArrayList<Book> list = new ArrayList<>();
		list.add(book);
		Mockito.when(iReviewService.listMostFavoredBooks()).thenReturn(list);
		mvc.perform(get("/review/favoredBook").contentType("application/json")).andExpect(status().isOk());
	}

//	----------------------List Review By Most Favored Books Invalid----------------------	
	@Test
	public void testListReviewByMostFavoredBooksInvalid() throws Exception {
		
		Mockito.when(iReviewService.listMostFavoredBooks()).thenThrow(ReviewNotFoundException.class);
		mvc.perform(get("/review/favoredBook").contentType("application/json")).andExpect(status().is(404));

	}

}
