package com.simplilearn.webservice.exceptions;

public class InvalidProductException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	String message;
	
	public InvalidProductException(String message) {
		super(message);
	}
}
