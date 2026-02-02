package com.example.springBootDemoApp.fileUploadAbstracted;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/file")
public class FileDocumentController {
	
	private final FileDocumentService fileDocumentService;
	
	public FileDocumentController(FileDocumentService fileDocumentService) {
		this.fileDocumentService = fileDocumentService;
	}
	
	// ---------- BINARY UPLOAD (multipart/form-data) ----------
	@PostMapping(
			value = "/upload/binary",
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE
	)
	public ResponseEntity<Long> uploadBinary(
			@RequestPart("file") MultipartFile file
	) throws Exception {
		
		FileDocument saved = fileDocumentService.storeAsBinary(file);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved.getId());
	}
	
	// ---------- BASE64 UPLOAD (application/json) ----------
	@PostMapping(
			value = "/upload/base64",
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE
	)
	public ResponseEntity<Long> uploadBase64(
			@RequestPart("file") MultipartFile file
	) throws Exception {
		
		FileDocument saved = fileDocumentService.storeAsBase64(file);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved.getId());
	}
	
	// ---------- DOWNLOAD ----------
	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
		
		FileDownloadResponse file = fileDocumentService.downloadFile(id);
		
		return ResponseEntity.ok()
					   .contentType(MediaType.parseMediaType(file.getContentType()))
					   .header(
							   HttpHeaders.CONTENT_DISPOSITION,
							   "attachment; filename=\"" + file.getFileName() + "\""
					   )
					   .body(file.getData());
	}
	
	
	// ---------- STREAM UPLOAD ----------
	@PostMapping(
			value = "/upload",
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE
	)
	public ResponseEntity<Long> upload(
			@RequestParam FileDocument.FileHandlingMode mode,
			@RequestPart("file") MultipartFile file
	) throws Exception {
		
		FileDocument saved;
		
		switch (mode) {
			case BINARY_DB -> saved = fileDocumentService.storeAsBinary(file);
			case BASE64_DB -> saved = fileDocumentService.storeAsBase64(file);
			case FS_STREAM -> saved = fileDocumentService.storeFileSystem(file);
			default -> throw new IllegalArgumentException("Unsupported mode");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(saved.getId());
	}
	
	// ---------- STREAM DOWNLOAD ----------
	@GetMapping("/download/stream/{id}")
	public ResponseEntity<StreamingResponseBody> download(@PathVariable Long id) {
		
		FileStreamResponse response = fileDocumentService.downloadStream(id);
		
		return ResponseEntity.ok()
					   .contentType(MediaType.parseMediaType(response.contentType()))
					   .header(
							   HttpHeaders.CONTENT_DISPOSITION,
							   "attachment; filename=\"" + response.fileName() + "\""
					   )
					   .body(response.stream());
	}
}