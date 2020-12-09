package com.bookstore.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.exception.BookNotFoundException;
import com.bookstore.exception.CustomerNotFoundException;
import com.bookstore.exception.InternalServerException;
import com.bookstore.exception.NotFoundException;
import com.bookstore.exception.ReviewNotFoundException;
import com.bookstore.exception.UserNotFoundException;

@RestController
@ControllerAdvice
public class RestExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> notFoundException(NotFoundException notFoundException) {
		return new ResponseEntity<Object>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InternalServerException.class)
	public ResponseEntity<Object> internalServerException(InternalServerException internalServerException) {
		return new ResponseEntity<Object>(internalServerException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "review not found")
	@ExceptionHandler(ReviewNotFoundException.class)
	public ResponseEntity<Object> handlerReviewNotFoundException(ReviewNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<Object> bookNotFoundException(BookNotFoundException bookNotFoundException){
		return new ResponseEntity<Object>(bookNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Invalid Customer ID") //404
	public ResponseEntity<Object> handleCustomerNotFound(CustomerNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Invalid Customer ID") //404
	public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}
