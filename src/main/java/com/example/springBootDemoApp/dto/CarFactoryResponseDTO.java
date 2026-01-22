package com.example.springBootDemoApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	
	public CarFactoryResponseDTO() {
	}
	

	public Long getCarModel() {
		return carModel;
	}

	public void setCarModel(Long carModel) {
		this.carModel = carModel;
	}

	public int getCarMakeYear() {
		return carMakeYear;
	}

	public void setCarMakeYear(int carMakeYear) {
		this.carMakeYear = carMakeYear;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarCompany() {
		return carCompany;
	}

	public void setCarCompany(String carCompany) {
		this.carCompany = carCompany;
	}
}