package com.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookstore.entities.Book;

public interface IBookRepository extends JpaRepository<Book, Integer>{

	@Query("FROM Book WHERE title=:titl")
	Book findByTitle(@Param("titl") String title);
	
	List<Book> findByDescriptionContains(String keyword);
	
	@Query("FROM Book WHERE category=:cat")
	List<Book> listBooksByCategory(@Param("cat") String category);
	
	@Query("FROM Book b WHERE b.review.rating>3.5")
	List<Book> listBestSellingBook();
}
