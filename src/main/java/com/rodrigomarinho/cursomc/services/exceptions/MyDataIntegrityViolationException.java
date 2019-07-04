package com.rodrigomarinho.cursomc.services.exceptions;

public class MyDataIntegrityViolationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public MyDataIntegrityViolationException(String msg) {
		super(msg);
	}
	
	public MyDataIntegrityViolationException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
