package com.bookstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entities.Book;
import com.bookstore.entities.Customer;
import com.bookstore.entities.Review;
import com.bookstore.exception.ReviewNotFoundException;
import com.bookstore.repositories.IReviewRepository;

@Service
public class ReviewService implements IReviewService {

	@Autowired
	private IReviewRepository iReviewRepository;

	@Override
	public List<Review> listAllReviews() throws ReviewNotFoundException {
		List<Review> list = null;
		list = iReviewRepository.findAll();
		if (list == null || list.isEmpty()) {
			throw new ReviewNotFoundException("Review is unavailable");
		}
		return list;
	}

	@Override
	public Review addReview(Review review) throws ReviewNotFoundException {
		if (review == null || review.getHeadLine().isEmpty() || review.getHeadLine() == null) {
			throw new ReviewNotFoundException("Review is unavailable");
		}

		return iReviewRepository.saveAndFlush(review);
	}

	@Override
	public Review viewReview(Review review) throws ReviewNotFoundException {
		if (review == null) {
			throw new ReviewNotFoundException("Review not present");
		}
		Optional<Review> viewReview = iReviewRepository.findById(review.getReviewId()); // same as review object
		if (viewReview.isPresent())
			return viewReview.get();
		else
			throw new ReviewNotFoundException("Review not present");
	}

	@Override
	public Review updateReview(Review review) throws ReviewNotFoundException {
		if (review == null) {
			throw new ReviewNotFoundException("Review is unavailable");
		}
		Optional<Review> optionalReview = iReviewRepository.findById(review.getReviewId());
		if (optionalReview.isPresent())
			return iReviewRepository.save(review);
		else
			throw new ReviewNotFoundException("Review Not present");
	}

	@Override
	public Review deleteReview(Review review) throws ReviewNotFoundException {
		if (!iReviewRepository.existsById(review.getReviewId())) {
			throw new ReviewNotFoundException("Review is unavailable");
		}
		iReviewRepository.delete(review);
		return review;
	}

	@Override
	public List<Review> listAllReviewsByBook(Book book) throws ReviewNotFoundException {

		if (book == null) {
			throw new ReviewNotFoundException("Review is unavailable");
		}
		List<Review> reviewListByBook = iReviewRepository.listReviewsByBook(book);
		if (reviewListByBook.isEmpty())
			throw new ReviewNotFoundException("Review is unavailable");
		else
			return reviewListByBook;
	}

	@Override
	public List<Review> listAllReviewsByCustomer(Customer c) throws ReviewNotFoundException {

		if (c == null) {
			throw new ReviewNotFoundException("Review is unavailable");
		}
		List<Review> reviewListByCustomer = iReviewRepository.listReviewsByCustomer(c);
		if (reviewListByCustomer.isEmpty())
			throw new ReviewNotFoundException("Review is unavailable");
		else
			return reviewListByCustomer;
	}

	@Override
	public List<Book> listMostFavoredBooks() {
		return iReviewRepository.listByFavoredBooks();
	}

}
