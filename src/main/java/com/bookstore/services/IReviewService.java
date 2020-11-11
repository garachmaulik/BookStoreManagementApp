package com.bookstore.services;

import java.util.List;

import com.bookstore.entities.Book;
import com.bookstore.entities.Customer;
import com.bookstore.entities.Review;
import com.bookstore.exception.ReviewNotFoundException;


public interface IReviewService {
	
	public List<Review> listAllReviews() throws ReviewNotFoundException;

	public Review addReview(Review review) throws ReviewNotFoundException;

	public Review deleteReview(Review review)throws ReviewNotFoundException;

	public Review updateReview(Review review) throws ReviewNotFoundException;

	public Review viewReview(Review review) throws ReviewNotFoundException;

	public List<Review> listAllReviewsByBook(Book book) throws ReviewNotFoundException;

	public List<Review> listAllReviewsByCustomer(Customer c) throws ReviewNotFoundException;

	public List<Book> listMostFavoredBooks() throws ReviewNotFoundException;

}

//public Review getReview(int reviewId) throws ReviewNotFoundException;
