package com.udacity.jdnd.course3.critter.service.exception;

import com.udacity.jdnd.course3.critter.utils.MESSAGES;

public class UnSupportedActivityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnSupportedActivityException() {
	    super(MESSAGES.EXCEPTIONS.UN_SUPPORTED_ACTIVITY);
	  }
}
