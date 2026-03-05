package com.example.session08.controller;

import com.example.session08.exception.FileValidateException;
import com.example.session08.exception.ResourceConflictException;
import com.example.session08.model.dto.ApiResponse;
import com.example.session08.model.dto.ReaderCreateDTO;
import com.example.session08.model.entity.Reader;
import com.example.session08.service.ReaderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/readers")
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    @PostMapping
    public ResponseEntity<ApiResponse<Reader>> createReader(@Valid @ModelAttribute ReaderCreateDTO request) throws FileValidateException, ResourceConflictException {
        return new ResponseEntity<>(readerService.createReader(request), HttpStatus.CREATED);
    }
}
