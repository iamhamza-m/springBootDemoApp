package com.example.springBootDemoApp;

import com.example.springBootDemoApp.dto.CarFactoryRequestDTO;
import com.example.springBootDemoApp.dto.CarFactoryResponseDTO;
import com.example.springBootDemoApp.exception.CarNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarFactoryService {
	private final CarFactoryRepository repository;
	
	public CarFactoryService(CarFactoryRepository repository) {
		this.repository = repository;
	}
	
	private static final Logger log = LoggerFactory.getLogger(CarFactoryService.class);
	
	public List<CarFactory> getAllCars(){
		List<CarFactory> data = repository.findAll();
		return data;
	}
	
	public CarFactory getCarByID(Long id){
		CarFactory data = repository.findById(id)
								  .orElseThrow(() -> new CarNotFoundException("No car details found with this id : " + id));
		return data;
	}
	
	public CarFactoryResponseDTO saveCarFactory(CarFactoryRequestDTO request){
		CarFactory entity = new CarFactory();
		entity.setCarMakeYear(request.getCarMakeYear());
		entity.setCarName(request.getCarName());
		entity.setCarCompany(request.getCarCompany());
		
		CarFactory saved = repository.save(entity);
		
		return new CarFactoryResponseDTO(
				saved.getCarModel(),
				saved.getCarMakeYear(),
				saved.getCarName(),
				saved.getCarCompany()
		);
	}
	
	public List<CarFactory> saveCarInBulk(List<CarFactory> data){
		log.debug("Saving {} cars", data.size());
		return repository.saveAll(data);
	}
}
