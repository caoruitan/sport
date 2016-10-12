package org.cd.sport.exception;

/**
 * 没有操作权限异常
 * 
 * @author liuyk
 *
 */
public class ForbiddenExcetion extends RuntimeException {

	private static final long serialVersionUID = -5147209287605509283L;

	public ForbiddenExcetion() {
		super();
	}

	public ForbiddenExcetion(String message) {
		super(message);
	}

	public ForbiddenExcetion(String message, Throwable cause) {
		super(message, cause);
	}
}
