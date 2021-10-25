package com.udacity.jdnd.course3.critter.service.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

	@ResponseBody
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String entityNotFoundException(EntityNotFoundException ex) {
		return ex.getMessage();
	}
	
	
	@ResponseBody
	@ExceptionHandler(UnSupportedActivityException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String unSupportedActivityException(UnSupportedActivityException ex) {
		return ex.getMessage();
	}
	
	
	
	@ResponseBody
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String constraintViolationException(ConstraintViolationException ex) {
		return ex.getMessage();
	}
}
