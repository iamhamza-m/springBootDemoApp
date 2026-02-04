package com.example.springBootDemoApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarFactoryResponseDTO {
	
	private Long carModel;
	private int carMakeYear;
	private String carName;
	private String carCompany;
	
	public CarFactoryResponseDTO(Long carModel, int carMakeYear, String carName, String carCompany) {
		this.carModel = carModel;
		this.carMakeYear = carMakeYear;
		this.carName = carName;
		this.carCompany = carCompany;
	}
	
}