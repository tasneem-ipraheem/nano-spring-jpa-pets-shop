package com.udacity.jdnd.course3.critter.service.exception;

import com.udacity.jdnd.course3.critter.utils.MESSAGES;

public class EntityNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(Long id) {
	    super(MESSAGES.EXCEPTIONS.ID_NOT_FOUND + id);
	  }
}
