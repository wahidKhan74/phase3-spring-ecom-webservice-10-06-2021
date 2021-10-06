package com.simplilearn.webservice.exceptions;

public class ProductAlreadyException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	String message;
	
	public ProductAlreadyException(String message) {
		super(message);
	}
}
