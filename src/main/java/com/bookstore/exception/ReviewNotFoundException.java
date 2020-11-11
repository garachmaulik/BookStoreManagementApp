package com.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReviewNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReviewNotFoundException() {
		super();
	}
	

	public ReviewNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ReviewNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReviewNotFoundException(String message) {
		super(message);
	}

	public ReviewNotFoundException(Throwable cause) {
		super(cause);
	}
}
