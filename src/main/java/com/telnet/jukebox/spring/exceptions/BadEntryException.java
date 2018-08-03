package com.telnet.jukebox.spring.exceptions;

import javax.ws.rs.BadRequestException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class BadEntryException extends BadRequestException {

	private static final long serialVersionUID = -9010214624259684608L;

	public static final Logger logger = LogManager.getLogger(BadEntryException.class);

	public BadEntryException() {
		logger.error("Invalid data entry!");
	}

}
