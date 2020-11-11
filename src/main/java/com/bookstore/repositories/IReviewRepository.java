package com.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookstore.entities.Book;
import com.bookstore.entities.Customer;
import com.bookstore.entities.Review;

public interface IReviewRepository extends JpaRepository<Review, Integer> {

	@Query("FROM Review where book=:book")
	List<Review> listReviewsByBook(@Param("book") Book book);

	List<Review> findByBookContains(Book book);

	@Query("FROM Review where customer=:cust")
	List<Review> listReviewsByCustomer(@Param("cust") Customer c);

	// List<Review> findByCustomer(Customer c);

	@Query("FROM Review WHERE rating>=3.5")
	List<Book> listByFavoredBooks();

}
