package com.telnet.jukebox.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.TokenExpiredException;

/*import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;*/

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ExpTokenException extends TokenExpiredException{

	public ExpTokenException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 3249468953947490938L;

	/*public ExpTokenException(Header<?> header, Claims claims, String message) {
		super(header, claims, message);
	}*/

}
