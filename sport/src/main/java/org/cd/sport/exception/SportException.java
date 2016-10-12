package org.cd.sport.exception;

/**
 * 系统全局异常
 * 
 * @author liuyk
 *
 */
public class SportException extends Exception {

	private static final long serialVersionUID = -5147209287605509281L;

	public SportException() {
		super();
	}

	public SportException(String message) {
		super(message);
	}

	public SportException(String message, Throwable cause) {
		super(message, cause);
	}

}
