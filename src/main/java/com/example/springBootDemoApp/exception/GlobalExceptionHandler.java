package com.example.springBootDemoApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception exception) {
		ErrorResponse error = new ErrorResponse(
				exception.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value()
		);
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
}
