package com.example.session08.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.session08.exception.FileValidateException;
import com.example.session08.exception.ResourceConflictException;
import com.example.session08.model.dto.ApiResponse;
import com.example.session08.model.dto.ReaderCreateDTO;
import com.example.session08.model.entity.Reader;
import com.example.session08.repository.ReaderRepository;
import com.example.session08.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ReaderServiceImpl implements ReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public ApiResponse<Reader> createReader(ReaderCreateDTO request) throws ResourceConflictException, FileValidateException {
        Reader reader = new Reader();
        String imageUrl = null;
        if(readerRepository.existsByEmail(request.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }

        MultipartFile file = request.getAvatarFile();
        if(file == null || file.isEmpty()){
            throw new FileValidateException("Avatar file is required");
        }

        String contentType = file.getContentType();
        if (contentType == null || !(contentType.equals("image/jpeg") || contentType.equals("image/png") || contentType.equals("image/jpg"))) {
            throw new FileValidateException("Only JPEG, PNG, and JPG are allowed.");
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !originalFilename.toLowerCase().matches(".*\\.(jpg|jpeg|png)$")){
            throw new FileValidateException("Invalid file extension.");
        }

        try {
            Map result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

            imageUrl = (String) result.get("url");
        } catch (IOException e) {
            throw new FileValidateException("Failed to upload avatar");
        }

        reader.setEmail(request.getEmail());
        reader.setFullName(request.getFullName());
        reader.setPhoneNumber(request.getPhoneNumber());
        reader.setAddress(request.getAddress());
        reader.setAvatar(imageUrl);
        readerRepository.save(reader);
        return new ApiResponse<>("SUCCESS", "Reader created successfully", reader);
    }
}
