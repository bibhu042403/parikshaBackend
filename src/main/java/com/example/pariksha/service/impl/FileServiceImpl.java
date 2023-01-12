package com.example.pariksha.service.impl;

import com.example.pariksha.response.Response;
import com.example.pariksha.service.FileService;
import com.example.pariksha.utlis.CryptoUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public void mergePdf(List<String> fileList) {
        List<byte[]> byteList = new ArrayList<>();
        byte[] mergedFile;
        try {
            for (String path : fileList) {
                byte[] fileArray = Files.readAllBytes(Paths.get(path));
                byteList.add(fileArray);
            }
            mergedFile = mergeAllByteArray(byteList);
            FileOutputStream fout = new FileOutputStream("/Users/bibhubhushan/Desktop/mergedPdf/merged123.pdf");
            fout.write(mergedFile);
            fout.close();
        } catch(Exception e){
            System.out.println("Exception occurred while converting "+ e);
        }
    }
    public byte[] mergeAllByteArray(List<byte[]> fileList) throws IOException {
        ByteArrayOutputStream fos = new ByteArrayOutputStream();
        for(byte[] by:fileList){
            fos.write(by);
        }
        byte[] finalre =  fos.toByteArray();
        fos.close();
        return finalre;
    }

}
