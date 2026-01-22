package com.example.springBootDemoApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarFactoryRepository extends JpaRepository<CarFactory, Long> {

}
