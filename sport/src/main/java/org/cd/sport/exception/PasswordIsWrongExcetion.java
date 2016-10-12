package org.cd.sport.exception;

/**
 * 实体没有找到异常
 * 
 * @author liuyk
 *
 */
public class PasswordIsWrongExcetion extends RuntimeException {

	private static final long serialVersionUID = -5147209286605509283L;

	public PasswordIsWrongExcetion() {
		super();
	}

	public PasswordIsWrongExcetion(String message) {
		super(message);
	}

	public PasswordIsWrongExcetion(String message, Throwable cause) {
		super(message, cause);
	}
}
