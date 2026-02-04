package com.example.springBootDemoApp.fileUploadAbstracted;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDocumentRepository extends JpaRepository<FileDocument, Long> {
}