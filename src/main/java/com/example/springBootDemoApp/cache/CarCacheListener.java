package com.example.springBootDemoApp.cache;

import com.example.springBootDemoApp.CarFactory;
import com.example.springBootDemoApp.event.CarSavedEvent;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class CarCacheListener {
	
	@CachePut(value = "cars", key = "#event.car.carModel")
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public CarFactory updateCache(CarSavedEvent event) {
		return event.getCar();
	}
}
