package org.objectquery.generic;

public class ObjectQueryException extends RuntimeException {

	private static final long serialVersionUID = 2969240402118300206L;

	public ObjectQueryException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectQueryException(String message) {
		super(message);
	}

}
