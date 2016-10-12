package org.cd.sport.exception;

/**
 * 实体没有找到异常
 * 
 * @author liuyk
 *
 */
public class EntityNotFoundExcetion extends RuntimeException {

	private static final long serialVersionUID = -5147209286605509283L;

	public EntityNotFoundExcetion() {
		super();
	}

	public EntityNotFoundExcetion(String message) {
		super(message);
	}

	public EntityNotFoundExcetion(String message, Throwable cause) {
		super(message, cause);
	}
}
