package com.rest.security.exceptions;

public class TokenNonInseritoException extends Exception {
	
	private static final long serialVersionUID = -7878138062551656181L;

	public TokenNonInseritoException(){
		super();
	}

	public TokenNonInseritoException(String message){
		super(message);
	}
}
