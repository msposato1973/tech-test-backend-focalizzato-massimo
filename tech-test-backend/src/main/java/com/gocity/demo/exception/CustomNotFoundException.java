package com.gocity.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	/***
	 * 
	 * @param messege
	 */
	
	public CustomNotFoundException(String messege) {
	    super(messege);
	}
}
