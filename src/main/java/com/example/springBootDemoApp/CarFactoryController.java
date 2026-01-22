package com.example.springBootDemoApp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarFactoryController {
	private final CarFactoryService service;
	
	@GetMapping
	public List<CarFactory> getAllCarDetails(){
		return service.getAllCars();
	}
	
	@GetMapping("/{id}")
	public CarFactory getCarByModel(@PathVariable Long id){
		return service.getCarByID(id);
	}
	
	@PostMapping("/saveNew")
	public CarFactoryResponseDTO saveNewCarDetails(@RequestBody CarFactoryRequestDTO request){
		return service.saveCarFactory(request);
	}
	
	@PostMapping("/saveNewBulk")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<List<CarFactory>> saveNewCarsInBulk(@RequestBody List<CarFactory> data){
		List<CarFactory> savedData = service.saveCarInBulk(data);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedData);
	}
}
