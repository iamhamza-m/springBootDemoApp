package com.example.springBootDemoApp;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "carfactory")
@Setter
@Getter
@RequiredArgsConstructor
public class CarFactory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long carModel;
	private int carMakeYear;
	private String carName;
	private String carCompany;
	
	@Override
	public String toString() {
		return "CarFactory{" +
					   "carModel=" + carModel +
					   ", carMakeYear=" + carMakeYear +
					   ", carName='" + carName + '\'' +
					   ", carCompany='" + carCompany + '\'' +
					   '}';
	}
}
