package com.sharekhan.http.exceptions;

public class SharekhanAPIException extends Throwable{
	
	private static final long serialVersionUID = 1L;
	public String message;
	public String code;
	
	public SharekhanAPIException(String message, String code) {
		this.message = message;
		this.code = code;
	}
	
	public SharekhanAPIException(String message) {
		this.message = message;
	}
	
	public String toString() {
		return "SharekhanApiException [message=" + message + ",code=" + code + "]";
	}
}
