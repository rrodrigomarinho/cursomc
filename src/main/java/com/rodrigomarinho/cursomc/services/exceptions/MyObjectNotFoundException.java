package com.rodrigomarinho.cursomc.services.exceptions;

public class MyObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public MyObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public MyObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
