package com.pitchbook.api.models.exceptions;

public class NoEntityFoundException extends Exception {
	
	public NoEntityFoundException() {}
	
	public NoEntityFoundException(String message) {
		super(message);
	}
	
	public NoEntityFoundException(Throwable cause) {
		super(cause);
	}
	
	public NoEntityFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
