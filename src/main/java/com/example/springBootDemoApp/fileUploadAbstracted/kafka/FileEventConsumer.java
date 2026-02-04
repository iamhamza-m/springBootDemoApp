package com.example.springBootDemoApp.fileUploadAbstracted.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FileEventConsumer {
	
	@KafkaListener(
			topics = "file.uploaded",
			groupId = "alfresco-ingestion-cg"
	)
	public void consume(String message) {
		
		System.out.println("Received file upload event: " + message);
		
		// Future steps:
		// 1. Parse event
		// 2. Download file from FS/S3
		// 3. Push to Alfresco
		// 4. Persist Alfresco documentId
	}
}