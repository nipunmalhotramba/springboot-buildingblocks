package com.stacksimplify.restservices.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {
	
	@ExceptionHandler(UserNameNotFoundException.class) 
	//Whenever this class exception happens invoke this method and that too invoke in for RestController
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CustomErrorDetails userNameNotFound(UserNameNotFoundException ex) {
		
		return new CustomErrorDetails(new Date(),
				"Defined in Global exception RestControllerAdvice User Name Not Found",
				ex.getMessage());
	}
	
}
