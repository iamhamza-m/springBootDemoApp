package com.example.springBootDemoApp.exception;

public class CarNotFoundException extends RuntimeException{
	public CarNotFoundException(String message) {
		super(message);
	}
}
