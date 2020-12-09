package com.bookstore.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bookstore.entities.Book;
import com.bookstore.entities.Customer;
import com.bookstore.entities.Review;
import com.bookstore.exception.ReviewNotFoundException;
import com.bookstore.services.IReviewService;

@CrossOrigin
@RestController
@RequestMapping("/review")
public class ReviewRestController {

	@Autowired
	private IReviewService IReviewService;

	// URL= http://localhost:8880/add
	@PostMapping(value = "/add", consumes = "application/json") //response entity
    @ExceptionHandler(ReviewNotFoundException.class)
    public Review addReview( @RequestBody Review review,  BindingResult result) throws ReviewNotFoundException {
        if(result.hasErrors()) {
            throw new ReviewNotFoundException(result.getFieldError().getDefaultMessage());
        }
        return IReviewService.addReview(review);
    }

	// URL= http://localhost:8880/view
	@PostMapping(value = "/view", consumes = "application/json")
	public Review viewReview(@RequestBody Review review) throws ReviewNotFoundException {
		return IReviewService.viewReview(review);
	}

	// URL= http://localhost:8880/update
	@PutMapping(value = "/update", consumes = "application/json")
	public Review updateReview(@RequestBody Review review) throws ReviewNotFoundException {
		return IReviewService.updateReview(review);
	}

	// URL= http://localhost:8880/get?id=123
	@DeleteMapping(value = "/delete", consumes = "application/json")
	public Review deleteReview(@RequestBody Review review) throws ReviewNotFoundException {
		return IReviewService.deleteReview(review);
	}

	// URL= http://localhost:8880/list
	@GetMapping(value = "/list", produces = "application/json")
	public List<Review> listReviews() throws ReviewNotFoundException {
		return IReviewService.listAllReviews();
	}
	

	// URL= http://localhost:8880/review/book
	@GetMapping(value = "/book", consumes = "application/json")
	public List<Review> getReviewsByBook(@RequestBody Book book) throws ReviewNotFoundException {
		return IReviewService.listAllReviewsByBook(book);
	}

	// URL= http://localhost:8880/review/customer
	@GetMapping(value = "/customer", consumes = "application/json")
	public List<Review> getReviewsByCustomer(@RequestBody Customer c) throws ReviewNotFoundException {
		return IReviewService.listAllReviewsByCustomer(c);
	}

	// URL= http://localhost:8880/review/book
	@GetMapping(value = "/favoredBook", produces = "application/json")
	public List<Book> getMostFavoredBooks() throws ReviewNotFoundException {
		return IReviewService.listMostFavoredBooks();
	}

}

