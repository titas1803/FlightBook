package com.cg.exceptions;

import java.util.List;

import org.springframework.validation.FieldError;

public class BookingExceptions extends Exception{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BookingExceptions(String message) {
		super(message);
		this.errors = null;
	}

	public BookingExceptions() {
		this.errors = null;
	}
	private final List<FieldError> errors;
	public BookingExceptions(List<FieldError> errors) {
		super();
		this.errors = errors;
	}

	public List<FieldError> getErrors() {
		return errors;
	}
	

}
