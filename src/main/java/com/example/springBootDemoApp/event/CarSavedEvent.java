package com.example.springBootDemoApp.event;

import com.example.springBootDemoApp.CarFactory;

public class CarSavedEvent {
	
	private final CarFactory car;
	
	public CarSavedEvent(CarFactory car) {
		this.car = car;
	}
	
	public CarFactory getCar() {
		return car;
	}
}