package com.example.springBootDemoApp.event;

import com.example.springBootDemoApp.CarFactory;
import lombok.Getter;

@Getter
public class CarSavedEvent {
	
	private final CarFactory car;
	
	public CarSavedEvent(CarFactory car) {
		this.car = car;
	}
	
}