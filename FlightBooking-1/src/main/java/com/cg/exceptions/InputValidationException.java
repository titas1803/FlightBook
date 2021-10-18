package com.cg.exceptions;

import java.util.List;

import org.springframework.validation.FieldError;

public class InputValidationException extends Exception {
	
	private final List<FieldError> errors;

	public InputValidationException() {
		super();
		this.errors = null;
	}

	public InputValidationException(String message) {
		super(message);
		this.errors = null;
	}

	public InputValidationException(List<FieldError> errors) {
		super();
		this.errors = errors;
	}
	
	

}
