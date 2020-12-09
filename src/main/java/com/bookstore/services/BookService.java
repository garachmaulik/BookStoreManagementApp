package com.bookstore.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entities.Book;
import com.bookstore.exception.BookNotFoundException;
import com.bookstore.repositories.IBookRepository;
import com.bookstore.repositories.ICategoryRepository;
@Service
public class BookService implements IBookService {

	@Autowired
	private IBookRepository ibookRepository;
	
	@Autowired
	private ICategoryRepository iCategoryRepository; 
	
	@Override
	public Book createBook(Book b) throws BookNotFoundException {
		if(b == null)
		{
			throw new BookNotFoundException("Book is null");
		}		
		b.setCategory(iCategoryRepository.getOne(b.getCategory().getCategoryId()));
		return ibookRepository.saveAndFlush(b);
	}

	@Override
	public List<Book> listAllBooks() throws BookNotFoundException{
		List<Book> books=new ArrayList<Book>();
		books=ibookRepository.findAll();
		if (books.isEmpty()) {
			throw new BookNotFoundException("Book list is empty");
		}
		return books;
	}

	@Override
	public Book deleteBook(Book b) throws BookNotFoundException{
		if(!ibookRepository.existsById(b.getBookId())) {
			throw new BookNotFoundException("Book not found");
		}
		ibookRepository.delete(b);
		return b;
	}

	@Override
	public Book editBook(Book b) throws BookNotFoundException{
		if(b == null) {
			throw new BookNotFoundException("No book found to be editted");
		}
		return ibookRepository.saveAndFlush(b);
	}

	@Override
	public Book viewBook(Book b) throws BookNotFoundException {
		if(!ibookRepository.existsById(b.getBookId())) {
			throw new BookNotFoundException("Book not found");
		}
		else {
		return ibookRepository.getOne(b.getBookId());
		}
	}

	@Override
	public Book findByTitle(String title) throws BookNotFoundException {
		Book books=new Book();
		books=ibookRepository.findByTitle(title);
		if(books == null)
		{
			throw new BookNotFoundException("Book not found");
		}
		return books;
	}

	@Override
	public List<Book> search(String keyword) throws BookNotFoundException {
		List<Book> books= new ArrayList<Book>();
		books=ibookRepository.findByDescriptionContains(keyword);
		if(books.isEmpty())
		{
			throw new BookNotFoundException("Book not found");
		}
		return books;
	}

	
	 @Override public List<Book> listRecentlyAdded() { 
		  
		  return null; }
	 

	@Override
	public List<Book> listBooksByCategory(String cat) throws BookNotFoundException {
		List<Book> books= new ArrayList<Book>();
		books=ibookRepository.listBooksByCategory(cat);
		if(books.isEmpty())
		{
			throw new BookNotFoundException("Book not found");
		}
		return books;
	}

	
	  @Override public List<Book> listBestSellingBook() throws BookNotFoundException { 
		  List<Book> books= new ArrayList<Book>();
			books=ibookRepository.listBestSellingBook();
			if(books.isEmpty())
			{
				throw new BookNotFoundException("Book not found");
			}
			return books;
		  }
}
