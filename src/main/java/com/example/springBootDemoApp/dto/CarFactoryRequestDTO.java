package com.example.springBootDemoApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CarFactoryRequestDTO {
	
	@NotNull(message = "The make year of a car cannot be blank.")
	private int carMakeYear;
	
	@NotBlank(message = "The car name cannot be blank.")
	private String carName;
	
	@NotBlank(message = "The car company cannot be blank.")
	private String carCompany;
}
