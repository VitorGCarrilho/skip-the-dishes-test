package com.skipthedishes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExists extends RuntimeException {
	
	private static final long serialVersionUID = -580648604506335861L;

	public EmailAlreadyExists(String msg){
		super(msg);
	}
}