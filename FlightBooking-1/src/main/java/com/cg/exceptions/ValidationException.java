package com.cg.exceptions;

import java.util.List;

import org.springframework.validation.FieldError;

public class ValidationException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final List<FieldError> errors;

	public List<FieldError> getErrors() {
		return errors;
	}

	public ValidationException() {
		super();
		this.errors = null;
	}

	public ValidationException(String message) {
		super(message);
		this.errors = null;
	}

	public ValidationException(List<FieldError> errors) {
		super();
		this.errors = errors;
	}
}
