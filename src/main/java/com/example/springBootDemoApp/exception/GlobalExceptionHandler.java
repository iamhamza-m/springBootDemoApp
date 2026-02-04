package com.example.springBootDemoApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(CarNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCarNotFound(CarNotFoundException exception) {
		ErrorResponse error = new ErrorResponse(
				exception.getMessage(),
				HttpStatus.NOT_FOUND.value()
		);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable( HttpMessageNotReadableException exception){
		
		ErrorResponse error = new ErrorResponse(
				"Something is wrong with the payload, please ensure the json is correct",
				HttpStatus.BAD_REQUEST.value()
		);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception){
		String errorMessage = exception.getBindingResult()
									  .getFieldErrors()
									  .stream()
									  .map(error -> error.getField() + " : Global Handler Error " + error.getDefaultMessage())
									  .findFirst()
									  .orElse("Invalid request please check the payload.");
		
		ErrorResponse error = new ErrorResponse(
				errorMessage,
				HttpStatus.INTERNAL_SERVER_ERROR.value()
		);
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception exception) {
		ErrorResponse error = new ErrorResponse(
				exception.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value()
		);
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
}
