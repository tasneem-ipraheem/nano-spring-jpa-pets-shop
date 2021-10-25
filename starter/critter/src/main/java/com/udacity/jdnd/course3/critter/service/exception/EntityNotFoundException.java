package com.udacity.jdnd.course3.critter.service.exception;

public class EntityNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(Long id) {
	    super("Could not find Entity With id = " + id);
	  }
}
