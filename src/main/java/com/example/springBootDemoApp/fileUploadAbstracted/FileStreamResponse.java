package com.example.springBootDemoApp.fileUploadAbstracted;

import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public record FileStreamResponse(
		String fileName,
		String contentType,
		StreamingResponseBody stream
) {}
