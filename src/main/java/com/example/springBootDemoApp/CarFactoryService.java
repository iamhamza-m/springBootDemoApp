package com.example.springBootDemoApp;

import com.example.springBootDemoApp.dto.CarFactoryRequestDTO;
import com.example.springBootDemoApp.dto.CarFactoryResponseDTO;
import com.example.springBootDemoApp.exception.CarNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarFactoryService {
	private final CarFactoryRepository repository;
	
	public CarFactoryService(CarFactoryRepository repository) {
		this.repository = repository;
	}
	
	private static final Logger log = LoggerFactory.getLogger(CarFactoryService.class);
	
	@Transactional(readOnly = true)
	@Cacheable(value = "allCars")
	public List<CarFactory> getAllCars(){
		return repository.findAll();
	}
	
	@Transactional(readOnly = true)
	@Cacheable(value = "cars", key = "#id")
	public CarFactory getCarByID(Long id){
		return repository.findById(id)
								  .orElseThrow(() -> new CarNotFoundException("No car details found with this id : " + id));
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	@CachePut(value = "cars", key = "#result.carModel")
	@CacheEvict(value = "allCars", allEntries = true)
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
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	@CacheEvict(value = {"cars", "allCars"}, allEntries = true)
	public List<CarFactory> saveCarInBulk(List<CarFactory> data){
		log.debug("Saving {} cars", data.size());
		return repository.saveAll(data);
	}
}
