package org.cd.sport.exception;

public class VerifieNotPassException extends RuntimeException {
	private static final long serialVersionUID = -5147209287605501121L;

	public VerifieNotPassException() {
		super();
	}

	public VerifieNotPassException(String message) {
		super(message);
	}

	public VerifieNotPassException(String message, Throwable cause) {
		super(message, cause);
	}
}
