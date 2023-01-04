package com.example.pariksha.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    ResponseEntity<byte[]> encryptSqlFile(MultipartFile file);
}
