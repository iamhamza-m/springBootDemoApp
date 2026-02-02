package com.example.springBootDemoApp.fileUploadAbstracted;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Service
public class FileDocumentService {
	
	private final FileDocumentRepository fileDocumentRepository;
	
	public FileDocumentService(FileDocumentRepository fileDocumentRepository) {
		this.fileDocumentRepository = fileDocumentRepository;
	}
	
	// ---------- STORE BINARY ----------
	@Transactional
	public FileDocument storeAsBinary(MultipartFile file) throws Exception {
		
		FileDocument doc = new FileDocument();
		doc.setFileName(file.getOriginalFilename());
		doc.setContentType(file.getContentType());
		doc.setSize(file.getSize());
		doc.setBinaryData(file.getBytes());
		doc.setStorageType(FileDocument.StorageType.BINARY);
		
		return fileDocumentRepository.save(doc);
	}
	
	// ---------- STORE BASE64 ----------
	@Transactional
	public FileDocument storeAsBase64(MultipartFile file) throws Exception {
		
		FileDocument doc = new FileDocument();
		doc.setFileName(file.getOriginalFilename());
		doc.setContentType(file.getContentType());
		doc.setSize(file.getSize());
		
		String base64 = Base64.getEncoder().encodeToString(file.getBytes());
		doc.setBase64Data(base64);
		
		doc.setBinaryData(null); // important
		doc.setStorageType(FileDocument.StorageType.BASE64);
		
		return fileDocumentRepository.save(doc);
	}
	
	// ---------- DOWNLOAD ----------
	public FileDownloadResponse downloadFile(Long id) {
		
		FileDocument doc = fileDocumentRepository.findById(id)
								   .orElseThrow(() -> new RuntimeException("File not found with id: " + id));
		
		byte[] data;
		
		if (doc.getStorageType() == FileDocument.StorageType.BINARY) {
			data = doc.getBinaryData();
		} else {
			data = Base64.getDecoder().decode(doc.getBase64Data());
		}
		
		return new FileDownloadResponse(
				doc.getFileName(),
				doc.getContentType(),
				data
		);
	}
}