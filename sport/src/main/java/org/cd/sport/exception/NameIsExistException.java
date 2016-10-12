package org.cd.sport.exception;

/**
 * 登录名重复
 * 
 * @author liuyk
 *
 */
public class NameIsExistException extends RuntimeException {

	private static final long serialVersionUID = -5147209287605509221L;

	public NameIsExistException() {
		super();
	}

	public NameIsExistException(String message) {
		super(message);
	}

	public NameIsExistException(String message, Throwable cause) {
		super(message, cause);
	}

}
