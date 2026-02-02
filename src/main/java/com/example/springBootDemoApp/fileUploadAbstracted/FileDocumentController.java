package com.example.springBootDemoApp.fileUploadAbstracted;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
}