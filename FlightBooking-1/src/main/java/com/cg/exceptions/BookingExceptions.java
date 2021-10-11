package com.cg.exceptions;

import java.util.List;

import org.springframework.validation.FieldError;

public class BookingExceptions extends Exception{
	
	

	public BookingExceptions(String message) {
		super(message);
		this.errors = null;
	}

	public BookingExceptions() {
		this.errors = null;
	}
	public final List<FieldError> errors;
	public BookingExceptions(List<FieldError> errors) {
		super();
		this.errors = errors;
	}

	public List<FieldError> getErrors() {
		return errors;
	}
	

}
