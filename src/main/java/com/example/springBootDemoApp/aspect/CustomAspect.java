package com.example.springBootDemoApp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomAspect {
	
	@Around("@annotation(custom)")
	public Object handleCustom(ProceedingJoinPoint joinPoint, Custom custom) throws Throwable {
		
		// Before method execution
		System.out.println("Custom annotation found with value: " + custom.value());
		
		Object result = joinPoint.proceed(); // invoke actual method
		
		// After method execution
		return result;
	}
}
