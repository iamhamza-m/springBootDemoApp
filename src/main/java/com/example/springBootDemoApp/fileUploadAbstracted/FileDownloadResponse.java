package com.example.springBootDemoApp.fileUploadAbstracted;

import lombok.Getter;

@Getter
public class FileDownloadResponse {
	
	private final String fileName;
	private final String contentType;
	private final byte[] data;
	
	public FileDownloadResponse(String fileName, String contentType, byte[] data) {
		this.fileName = fileName;
		this.contentType = contentType;
		this.data = data;
	}
	
}