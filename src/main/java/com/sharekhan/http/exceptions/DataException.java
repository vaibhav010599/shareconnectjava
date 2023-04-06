package com.sharekhan.http.exceptions;
//Exceptions raised when invalid data is returned.

@SuppressWarnings("serial")
public class DataException extends SharekhanAPIException{

	 public DataException(String message, String code){
	        super(message, code);
	    }
}