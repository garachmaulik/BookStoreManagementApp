package com.bookstore.services;

import java.util.List;

import com.bookstore.entities.Book;
import com.bookstore.exception.BookNotFoundException;

public interface IBookService {

	public Book createBook(Book b) throws BookNotFoundException;
	public List<Book> listAllBooks() throws BookNotFoundException;
	public Book deleteBook(Book b) throws BookNotFoundException; 
	public Book editBook(Book b) throws BookNotFoundException;
	public Book viewBook(Book b) throws BookNotFoundException;
	public Book findByTitle(String title) throws BookNotFoundException;
	public List<Book> search(String keyword) throws BookNotFoundException;
	public List<Book> listRecentlyAdded();
	public List<Book> listBooksByCategory(String cat) throws BookNotFoundException;
	public List<Book> listBestSellingBook() throws BookNotFoundException;
	
}
