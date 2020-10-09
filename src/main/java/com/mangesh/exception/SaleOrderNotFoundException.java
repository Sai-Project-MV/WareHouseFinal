package com.mangesh.exception;

public class SaleOrderNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SaleOrderNotFoundException() {
		super();
	}

	public SaleOrderNotFoundException(String message) {
		super(message);
	}

}
