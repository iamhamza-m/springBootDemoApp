package com.example.springBootDemoApp;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "carfactory")
public class CarFactory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long carModel;

	private int carMakeYear;
	private String carName;
	private String carCompany;
	
	public CarFactory(int carMakeYear, String carName, String carCompany) {
		this.carMakeYear = carMakeYear;
		this.carName = carName;
		this.carCompany = carCompany;
	}
	
	public CarFactory() {
	
	}
	
	@Override
	public String toString() {
		return "CarFactory{" +
					   "carModel=" + carModel +
					   ", carMakeYear=" + carMakeYear +
					   ", carName='" + carName + '\'' +
					   ", carCompany='" + carCompany + '\'' +
					   '}';
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
