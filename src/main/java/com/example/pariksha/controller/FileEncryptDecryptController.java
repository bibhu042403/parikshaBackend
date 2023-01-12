package com.example.pariksha.controller;


import com.example.pariksha.response.Response;
import com.example.pariksha.service.FileService;
import com.example.pariksha.utlis.CryptoUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Produces;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Bibhu Bhushan on 09/12/2022
 */

@RestController
@RequestMapping("/fileSystem")
public class FileEncryptDecryptController {

    @Autowired
    FileService fileService;

    @Produces(MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @PostMapping("/encryptFile")
    public ResponseEntity<Response<String>> encryptFile(@RequestParam("file") MultipartFile file) {
        String response = "";
//        return fileService.encryptSqlFile(file);
        ResponseEntity<byte[]> result =  fileService.encryptSqlFile(file);
        try {
            byte[] encryptedFile = result.getBody();
            if (encryptedFile != null) {
                Files.write(Paths.get("/Users/bibhubhushan/Desktop/encrypted_personal_password.txt"), encryptedFile);
                response = "File encrypted SuccessFully!";
            } else
                response = "Error while encrypting because its Null";
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.OK).body(Response.error("Exception occured :: " + e));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Response<>(response, response));
    }

    @PostMapping("/decryptFile")
    public ResponseEntity<Response<String>> decryptFile(@RequestParam String filePath, @RequestParam String saveFilePath){
        String response = "";
        try(InputStream inputStream = new FileInputStream(filePath))
        {
            InputStream decryptedStream = CryptoUtil.getInstance().decrypt(inputStream);
            try (FileOutputStream fos =
                         new FileOutputStream(saveFilePath)) {
                IOUtils.copy(decryptedStream, fos);
                response = "File decrypted successfully! ";
            }
        } catch (IOException e) {
                response = "Error while decrypting";
        }
        return ResponseEntity.status(HttpStatus.OK).body(new com.example.pariksha.response.Response<>(response, response));
    }

    @PostMapping("/mergePdf")
    public ResponseEntity<Response<String>> mergePdf(@RequestBody List<String> file){
        String status = "";
        try{
            if(!file.isEmpty()){
                fileService.mergePdf(file);
                status = "Success";
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.OK).body(Response.error("Exception occurred while merging " + e));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new Response<>(status, status));
    }
}
