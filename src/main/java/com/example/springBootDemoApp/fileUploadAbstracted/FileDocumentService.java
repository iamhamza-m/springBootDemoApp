package com.example.springBootDemoApp.fileUploadAbstracted;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileDocumentService {
	
	private final FileDocumentRepository fileDocumentRepository;
	private static final Path ROOT = Paths.get("uploads");
	
	public FileDocumentService(FileDocumentRepository fileDocumentRepository) throws IOException {
		this.fileDocumentRepository = fileDocumentRepository;
		Files.createDirectories(ROOT);
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
	
	// ---------- STORE FILE SYSTEM ----------
	public FileDocument storeFileSystem(MultipartFile file) throws IOException {
		
		Path target = ROOT.resolve(UUID.randomUUID() + "_" + file.getOriginalFilename());
		
		try (InputStream is = file.getInputStream()) {
			Files.copy(is, target, StandardCopyOption.REPLACE_EXISTING);
		}
		
		FileDocument doc = new FileDocument();
		doc.setFileName(file.getOriginalFilename());
		doc.setContentType(file.getContentType());
		doc.setSize(file.getSize());
		doc.setFilePath(target.toString());
		doc.setHandlingMode(FileDocument.FileHandlingMode.FS_STREAM);
		
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
	
	// ---------- STREAMED DOWNLOAD ----------
	public FileStreamResponse downloadStream(Long id) {
		
		FileDocument doc = fileDocumentRepository.findById(id)
								   .orElseThrow(() -> new RuntimeException("File not found"));
		
		StreamingResponseBody stream = outputStream -> {
			
			if (doc.getHandlingMode() == FileDocument.FileHandlingMode.BINARY_DB) {
				outputStream.write(doc.getBinaryData());
				
			} else if (doc.getHandlingMode() == FileDocument.FileHandlingMode.BASE64_DB) {
				byte[] decoded = Base64.getDecoder()
										 .decode(doc.getBase64Data());
				outputStream.write(decoded);
				
			} else if (doc.getHandlingMode() == FileDocument.FileHandlingMode.FS_STREAM) {
				Path path = Paths.get(doc.getFilePath());
				Files.copy(path, outputStream);
			}
			
			outputStream.flush();
		};
		
		return new FileStreamResponse(
				doc.getFileName(),
				doc.getContentType(),
				stream
		);
	}
	
	
}