package com.example.springBootDemoApp;

import com.example.springBootDemoApp.dto.CarFactoryRequestDTO;
import com.example.springBootDemoApp.dto.CarFactoryResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarFactoryController {
	private final CarFactoryService service;
	
	public CarFactoryController(CarFactoryService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<CarFactory> getAllCarDetails(){
		return service.getAllCars();
	}
	
	@GetMapping("/{id}")
	public CarFactory getCarByModel(@PathVariable Long id){
		return service.getCarByID(id);
	}
	
	@PostMapping("/saveNew")
	public CarFactoryResponseDTO saveNewCarDetails(@Valid @RequestBody CarFactoryRequestDTO request){
		return service.saveCarFactory(request);
	}
	
	@PostMapping("/saveNewBulk")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<List<CarFactory>> saveNewCarsInBulk(@Valid @RequestBody List<CarFactory> data){
		List<CarFactory> savedData = service.saveCarInBulk(data);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedData);
	}
}
