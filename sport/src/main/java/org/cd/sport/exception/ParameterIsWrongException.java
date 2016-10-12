package org.cd.sport.exception;

/**
 * 实体没有找到异常
 * 
 * @author liuyk
 *
 */
public class ParameterIsWrongException extends RuntimeException {

	private static final long serialVersionUID = -5147209286605509283L;

	public ParameterIsWrongException() {
		super();
	}

	public ParameterIsWrongException(String message) {
		super(message);
	}

	public ParameterIsWrongException(String message, Throwable cause) {
		super(message, cause);
	}
}
