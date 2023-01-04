package com.example.pariksha;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class main {

    public static void main(String[] args) throws IOException {
        // Destination of the ZIP file (an in-memory byte array)
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        File file = new File("/Users/bibhubhushan/Desktop/zipFileTest/bibhu.zip");

        /*
         * Write the ZIP file. This creates a single entry named "file.txt"
         * with "Hello, World!" as its contents.
         */
        Map<String,String> fileMap = new HashMap<>();
        fileMap.put("file1.txt","its file1");
        fileMap.put("file2.txt","its file2");
        fileMap.put("file3.txt","its file3");
        try (ZipOutputStream zos = new ZipOutputStream(boas)) {
            zos.putNextEntry(new ZipEntry("file.txt"));
            zos.write("Hello, World!".getBytes());
            zos.closeEntry();
        }
        catch(Exception e){
            System.out.println("Exception e" + e);
        }

        // Create an InputStream to read the raw bytes of the ZIP file
        ByteArrayInputStream bois = new ByteArrayInputStream(boas.toByteArray());

        /*
         * The following writes the ZIP file to disk, specifically to a file named "test.zip"
         * in the working directory. The purpose of this is to allow you to run the code
         * and see a tangible result (i.e. lets you inspect the resulting ZIP file). Obviously
         * you would not do this in your own code since you want to avoid writing the ZIP file
         * to disk.
         *
         * Note: Will fail if the file already exists.
         */
        Files.copy(bois, file.toPath());


    }
}
