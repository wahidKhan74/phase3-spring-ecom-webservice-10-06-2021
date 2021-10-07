package com.simplilearn.webservice.exceptions;

public class ProductAlreadyExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	String message;
	
	public ProductAlreadyExistException(String message) {
		super(message);
	}
}
