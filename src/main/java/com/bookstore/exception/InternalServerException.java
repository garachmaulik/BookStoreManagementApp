package com.bookstore.exception;

public class InternalServerException extends Exception{

	private static final long serialVersionUID = 1L;

	public InternalServerException() {
		super();
	}

	public InternalServerException(String message) {
		super(message);
	}

}
