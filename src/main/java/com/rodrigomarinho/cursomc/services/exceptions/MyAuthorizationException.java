package com.rodrigomarinho.cursomc.services.exceptions;

public class MyAuthorizationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public MyAuthorizationException(String msg) {
		super(msg);
	}
	
	public MyAuthorizationException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
