package com.example.springBootDemoApp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Before("execution(* com.example.interviewSpring.CarFactoryService..*(..))")
	public Object logServiceMethods(JoinPoint joinPoint) throws Throwable {
		
		long startTime = System.currentTimeMillis();
		
		log.info("Entering {} with arguments {}",
				joinPoint.getSignature().toShortString(),
				Arrays.toString(Arrays.stream(joinPoint.getArgs()).toArray()));
		
		try {
			Object result = joinPoint.getSignature();
			
			long timeTaken = System.currentTimeMillis() - startTime;
			
			log.info("Exiting {} | Time taken: {} ms",
					joinPoint.getSignature().toShortString(),
					timeTaken);
			
			return result;
			
		} catch (Exception ex) {
			log.error("Exception in {} | Message: {}",
					joinPoint.getSignature().toShortString(),
					ex.getMessage(), ex);
			throw ex;
		}
	}
}