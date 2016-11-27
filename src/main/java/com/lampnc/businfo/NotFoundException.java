package com.lampnc.businfo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	public NotFoundException() {
		this("The requested resource could not be found.");
	}

	public NotFoundException(String message) {
		this(message, null);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
