package com.example.pariksha.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface FileService {
    ResponseEntity<byte[]> encryptSqlFile(MultipartFile file);

    void mergePdf(List<String> file);
}
