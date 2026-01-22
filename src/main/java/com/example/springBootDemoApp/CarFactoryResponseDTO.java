package com.example.springBootDemoApp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarFactoryResponseDTO {
	
	private Long carModel;
	private int carMakeYear;
	private String carName;
	private String carCompany;
}