package com.example.springBootDemoApp.fileUploadAbstracted.kafka;

public class FileUploadedEvent {
	
	private final Long fileId;
	private final String fileName;
	private final String contentType;
	private final String handlingMode;
	
	public FileUploadedEvent(Long fileId, String fileName, String contentType, String handlingMode) {
		this.fileId = fileId;
		this.fileName = fileName;
		this.contentType = contentType;
		this.handlingMode = handlingMode;
	}
	
	public Long getFileId() {
		return fileId;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public String getHandlingMode() {
		return handlingMode;
	}
}