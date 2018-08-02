package com.telnet.jukebox.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.auth0.jwt.exceptions.TokenExpiredException;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ExpTokenException extends TokenExpiredException {

	public ExpTokenException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 3249468953947490938L;

}
