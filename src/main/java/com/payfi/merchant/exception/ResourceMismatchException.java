package com.payfi.merchant.exception;

public class ResourceMismatchException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public ResourceMismatchException() {
		super("Resource not found on server!!");
	}
	
	public ResourceMismatchException(String message) {
		super(message);
	}
	
}
