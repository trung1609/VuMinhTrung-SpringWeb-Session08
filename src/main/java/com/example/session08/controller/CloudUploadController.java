package com.example.session08.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.session08.config.CloudinaryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/files")
public class CloudUploadController {

    @Autowired
    private Cloudinary cloudinary;

    @PostMapping("/cloud")
    public ResponseEntity<Map> uploadCloud(@RequestParam("file") MultipartFile file){
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

            String imageUrl = (String) uploadResult.get("url");
            return ResponseEntity.ok(Map.of("url", imageUrl, "message", "File uploaded successfully"));
        }catch (IOException e){
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }
}
