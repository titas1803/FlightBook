package com.cg.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.exceptions.AlreadyExistsException;
import com.cg.exceptions.LoginException;
import com.cg.exceptions.NotFoundException;
import com.cg.exceptions.ValidationException;
import com.cg.util.ErrorMessage;

@RestControllerAdvice
public class FlightBookingAdvice {

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleExceptionNotFoundException(NotFoundException ex) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());

	}
	
	@ExceptionHandler(LoginException.class)
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public ErrorMessage handleExceptionLoginException(LoginException ex) {
		return new ErrorMessage(HttpStatus.FORBIDDEN.toString(), ex.getMessage());
		
	}
	
	@ExceptionHandler(AlreadyExistsException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleExceptionAlreadyExistsException(AlreadyExistsException ex) {
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), ex.getMessage());
		
	}
	
	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleExceptionValidationException(ValidationException ex) {
		List<String> errors=ex.getErrors().stream().map(err->err.getDefaultMessage()).collect(Collectors.toList());
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), errors);
		
	}

}
