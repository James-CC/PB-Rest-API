package com.pitchbook.api.models.exceptions;

public class AssociatedEntitiesExistException extends Exception {

	public AssociatedEntitiesExistException() {}
	
	public AssociatedEntitiesExistException(String message) {
		super(message);
	}
	
	public AssociatedEntitiesExistException(Throwable cause) {
		super(cause);
	}
	
	public AssociatedEntitiesExistException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
