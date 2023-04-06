package com.sharekhan.http.exceptions;

//Represents all order placement and manipulation errors.
//Default code is 500.

public class OrderException extends SharekhanAPIException {

	private static final long serialVersionUID = 1L;

	// initialize Order Exception and call base exception constructor
 public OrderException(String message, String code){
     super(message, code);
 }
}
