package com.example.springBootDemoApp.fileUploadAbstracted;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "file_document")
@Getter
@Setter
public class FileDocument {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fileName;
	private String contentType;
	private long size;
	
	@Lob
	@Column(columnDefinition = "BLOB")
	private byte[] binaryData;
	
	@Lob
	@Column(columnDefinition = "TEXT")
	private String base64Data;
	
	@Enumerated(EnumType.STRING)
	private StorageType storageType;
	
	public enum StorageType {
		BINARY,
		BASE64
	}
}