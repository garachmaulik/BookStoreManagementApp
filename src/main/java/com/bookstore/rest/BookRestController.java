package com.bookstore.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bookstore.entities.Book;
import com.bookstore.exception.BookNotFoundException;
import com.bookstore.services.IBookService;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookRestController {

	@Autowired
	private IBookService service;
	
	// URL: http://localhost:8880/add
	@PostMapping(value = "/add", consumes = "application/json")
	public Book createBook(@RequestBody Book b) throws BookNotFoundException {
		return service.createBook(b);
	}
	
	// URL: http://localhost:8880/list
	@GetMapping(value = "/list", produces = "application/json")
	public List<Book> listAllBooks() throws BookNotFoundException{
		return service.listAllBooks();
	}
	
	// URL: http://localhost:8880/delete
	@DeleteMapping(value = "/delete", consumes = "application/json")
	public Book deleteBook(@RequestBody Book b) throws BookNotFoundException {
		return service.deleteBook(b);
	}
	
	// URL: http://localhost:8880/edit
	@PostMapping(value = "/edit", consumes = "application/json")
	public Book editBook(Book b) throws BookNotFoundException {
		return service.editBook(b);
	}
	
	// URL: http://localhost:8880/view
	@GetMapping(value = "/view", consumes = "application/json")
	public Book viewBook(@RequestBody Book b) throws BookNotFoundException {
		return service.viewBook(b);
	}
	
	// URL: http://localhost:8880/title
	@GetMapping(value = "/title/{title}", produces = "application/json")
	public Book findBookByTitle(@PathVariable String title) throws BookNotFoundException {
		return service.findByTitle(title);
	}
	
	// URL: http://localhost:8880/title
	@GetMapping(value = "/keyword/{keyword}")
	public List<Book> search(@PathVariable String keyword) throws BookNotFoundException {
		return service.search(keyword);
	}
	
	/*
	 * // URL: http://localhost:8880/
	 * 
	 * @GetMapping(value = "/", consumes = "application/json") ??? public List<Book>
	 * listRecentlyAdded() { return service.listRecentlyAdded(); }
	 */
	
	// URL: http://localhost:8880/category
	@GetMapping(value = "/category{cat}")
	public List<Book> listBooksByCategory(@PathVariable String cat) throws BookNotFoundException {
		return service.listBooksByCategory(cat);
	}
	
	@GetMapping(value = "/listbooks")
	public List<Book> listBestSellingBook() throws BookNotFoundException{
		return service.listBestSellingBook();
	}
}
