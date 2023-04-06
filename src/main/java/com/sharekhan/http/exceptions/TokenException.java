package com.sharekhan.http.exceptions;

//Denotes session is expired

public class TokenException extends SharekhanAPIException{
	
	private static final long serialVersionUID = 1L;

	public TokenException(String message, String code) {
     super(message, code);
 }

}