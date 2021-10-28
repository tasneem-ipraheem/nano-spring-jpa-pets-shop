package com.udacity.jdnd.course3.critter.service.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

	@ResponseBody
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String entityNotFoundException(EntityNotFoundException ex) {
		return ex.getMessage();
	}
	
	
	@ResponseBody
	@ExceptionHandler(GeneralResponceException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String generalResponceException(GeneralResponceException ex) {
		return ex.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(AlreadyExistException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	String AlreadyExistException(AlreadyExistException ex) {
		return ex.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(GeneralServerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String generalServerException(GeneralServerException ex) {
		return ex.getMessage();
	}
	
	
	
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
	
//	@ResponseBody
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	String constraintViolationException(MethodArgumentNotValidException ex) {
//		String msg ="";
//		
//		 Map<String, String> errors = new HashMap<>();
//		 
//		    ex.getBindingResult().getAllErrors().forEach((error) -> {
//		        String fieldName = ((FieldError) error).getField();
//		        String errorMessage = error.getDefaultMessage();
////		        errors.put(fieldName, errorMessage);
//		        msg = msg + "[ fieldName : "+fieldName+" "+errorMessage+" ]";
//
//		    });
//		    return msg;
////		return ex.getMessage();
//	}
//	
	
}
