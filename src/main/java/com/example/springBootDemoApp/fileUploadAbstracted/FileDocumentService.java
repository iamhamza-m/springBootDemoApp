package com.example.springBootDemoApp.fileUploadAbstracted;

import com.example.springBootDemoApp.fileUploadAbstracted.kafka.FileEventProducer;
import com.example.springBootDemoApp.fileUploadAbstracted.kafka.FileUploadedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileDocumentService {
	
	private final FileDocumentRepository fileDocumentRepository;
	private final FileEventProducer fileEventProducer;
	
	private static final Path ROOT = Paths.get("uploads");
	
	public FileDocumentService(
			FileDocumentRepository fileDocumentRepository,
			FileEventProducer fileEventProducer
	) throws IOException {
		this.fileDocumentRepository = fileDocumentRepository;
		this.fileEventProducer = fileEventProducer;
		Files.createDirectories(ROOT);
	}
	
	private void publishEvent(FileDocument doc) {
		FileUploadedEvent event = new FileUploadedEvent(
				doc.getId(),
				doc.getFileName(),
				doc.getContentType(),
				doc.getHandlingMode().name()
		);
		
		fileEventProducer.publish(event);
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
		doc.setHandlingMode(FileDocument.FileHandlingMode.BINARY_DB);
		
		FileDocument saved = fileDocumentRepository.save(doc);
		
		publishEvent(saved);
		return saved;
	}
	
	// ---------- STORE BASE64 ----------
	@Transactional
	public FileDocument storeAsBase64(MultipartFile file) throws Exception {
		
		FileDocument doc = new FileDocument();
		doc.setFileName(file.getOriginalFilename());
		doc.setContentType(file.getContentType());
		doc.setSize(file.getSize());
		doc.setBase64Data(Base64.getEncoder().encodeToString(file.getBytes()));
		doc.setStorageType(FileDocument.StorageType.BASE64);
		doc.setHandlingMode(FileDocument.FileHandlingMode.BASE64_DB);
		
		FileDocument saved = fileDocumentRepository.save(doc);
		
		publishEvent(saved);
		return saved;
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
		
		FileDocument saved = fileDocumentRepository.save(doc);
		
		publishEvent(saved);
		return saved;
	}
	
	// ---------- DOWNLOAD ----------
	public FileDownloadResponse downloadFile(Long id) {
		
		FileDocument doc = fileDocumentRepository.findById(id)
								   .orElseThrow(() -> new RuntimeException("File not found with id: " + id));
		
		byte[] data = doc.getStorageType() == FileDocument.StorageType.BINARY
							  ? doc.getBinaryData()
							  : Base64.getDecoder().decode(doc.getBase64Data());
		
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
				outputStream.write(Base64.getDecoder().decode(doc.getBase64Data()));
				
			} else if (doc.getHandlingMode() == FileDocument.FileHandlingMode.FS_STREAM) {
				Files.copy(Paths.get(doc.getFilePath()), outputStream);
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