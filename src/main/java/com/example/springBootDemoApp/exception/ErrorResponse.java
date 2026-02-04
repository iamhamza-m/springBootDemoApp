package com.example.springBootDemoApp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
	
	private String message;
	private int status;
	private LocalDateTime timestamp;
	
	public ErrorResponse(String message, int status) {
		this.message = message;
		this.status = status;
		this.timestamp = LocalDateTime.now();
	}
	
}
