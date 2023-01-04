package com.example.pariksha.service.impl;

import com.example.pariksha.service.FileService;
import com.example.pariksha.utlis.CryptoUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Service
public class FileServiceImpl implements FileService {

    public ResponseEntity<byte[]> encryptSqlFile(MultipartFile file) {
        try {
            byte[] fileDataInBytes = file.getBytes();
            byte[] encryptedData = CryptoUtil.getInstance().encrypt(fileDataInBytes);
            String fileName = ("encrypted file" + ".txt").replaceAll("\\s", "_");
            return ResponseEntity.ok()
                    .contentLength(encryptedData.length)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=" + fileName)
                    .body(encryptedData);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
