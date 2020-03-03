package com.rodrigomarinho.cursomc.services.exceptions;

public class MyFileException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public MyFileException(String msg) {
		super(msg);
	}
	
	public MyFileException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
