package com.example.session08.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/files")
public class LocalUploadController {

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    @PostMapping("/upload-local")
    public ResponseEntity<String> uploadLocal(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        File destinationFile = new File(UPLOAD_DIR + fileName);

        if(!destinationFile.getParentFile().exists()) {
            destinationFile.getParentFile().mkdirs();
        }

        file.transferTo(destinationFile);
        return ResponseEntity.ok("File uploaded successfully to: " + destinationFile.getAbsolutePath());
    }
}
