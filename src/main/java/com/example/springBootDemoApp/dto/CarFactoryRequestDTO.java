package com.example.springBootDemoApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class CarFactoryRequestDTO {
	
	@NotNull(message = "The make year of a car cannot be blank.")
	private int carMakeYear;
	
	@NotBlank(message = "The car name cannot be blank.")
	private String carName;
	
	@NotBlank(message = "The car company cannot be blank.")
	private String carCompany;
	
	
	
	
	//Getters and Setters
	
	public String getCarName() {
		return carName;
	}
	
	public void setCarName(String carName) {
		this.carName = carName;
	}
	
	public int getCarMakeYear() {
		return carMakeYear;
	}
	
	public void setCarMakeYear(int carMakeYear) {
		this.carMakeYear = carMakeYear;
	}
	
	public String getCarCompany() {
		return carCompany;
	}
	
	public void setCarCompany(String carCompany) {
		this.carCompany = carCompany;
	}
}
