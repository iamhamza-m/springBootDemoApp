package com.example.springBootDemoApp.fileUploadAbstracted.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FileEventProducer {
	
	private static final String TOPIC = "file.uploaded";
	
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	public FileEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void publish(FileUploadedEvent event) {
		try {
			String payload = objectMapper.writeValueAsString(event);
			kafkaTemplate.send(TOPIC, event.getFileId().toString(), payload);
		} catch (Exception e) {
			throw new RuntimeException("Failed to publish Kafka event", e);
		}
	}
}