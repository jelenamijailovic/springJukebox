package com.telnet.jukebox.spring.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.auth0.jwt.exceptions.TokenExpiredException;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ExpTokenException extends TokenExpiredException {

	private static final long serialVersionUID = 3249468953947490938L;

	public static final Logger logger = LogManager.getLogger(ExpTokenException.class);

	public ExpTokenException(String message) {
		super(message);
		logger.error("Token has been expired!");
	}

}
