package com.stacksimplify.restservices.exceptions;

import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	//Override default MethodArgumentNotValid from the ResponseEntityExceptionHandler by copying its method
	//Removing the return statement
	//Referencing CustomErrorDetails class
	//Returning attributes we want to pass on calling API for CreateUser (POST create user)
	
	/**
	 * Customize the response for MethodArgumentNotValidException.
	 * <p>This method delegates to {@link #handleExceptionInternal}.
	 * @param ex the exception
	 * @param headers the headers to be written to the response
	 * @param status the selected response status
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		CustomErrorDetails customerrordetails=new CustomErrorDetails(new Date(), 
				"Override MethodArgumentNotValid using global exception handler",
				ex.getMessage());
		return new ResponseEntity<>(customerrordetails,HttpStatus.BAD_REQUEST);
		
		//return handleExceptionInternal(ex, null, headers, status, request);
	}
	
	
	/**
	 * Customize the response for HttpRequestMethodNotSupportedException.
	 * <p>This method logs a warning, sets the "Allow" header, and delegates to
	 * {@link #handleExceptionInternal}.
	 * @param ex the exception
	 * @param headers the headers to be written to the response
	 * @param status the selected response status
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 */
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		/*
		 * pageNotFoundLogger.warn(ex.getMessage());
		 * 
		 * Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods(); if
		 * (!CollectionUtils.isEmpty(supportedMethods)) {
		 * headers.setAllow(supportedMethods); } return handleExceptionInternal(ex,
		 * null, headers, status, request);
		 */
		
		CustomErrorDetails customerrordetails = new CustomErrorDetails(new Date(),
				"You are trying a method maybe Patch request for which i have not yet coded in the Controller",
				ex.getMessage());
		
		return new ResponseEntity<Object> (customerrordetails,HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(UserNotFoundException.class) //Tie to a class
	//Create your own Handler
	public final ResponseEntity<Object> handleUserNotFoundException(
			 UserNotFoundException ex, WebRequest request) {
		
		CustomErrorDetails customerrordetails = new CustomErrorDetails(new Date(),
				"Defined in global exception handler - "+ ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<Object> (customerrordetails,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolatoinException(ConstraintViolationException ex, WebRequest request) {
		
		CustomErrorDetails customerrordetails = new CustomErrorDetails(new Date(),
				"Defined in global exception handler - "+ ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<Object> (customerrordetails,HttpStatus.BAD_REQUEST);
		
	}
	
}
