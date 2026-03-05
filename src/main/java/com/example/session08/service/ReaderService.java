package com.example.session08.service;

import com.example.session08.exception.FileValidateException;
import com.example.session08.exception.ResourceConflictException;
import com.example.session08.model.dto.ApiResponse;
import com.example.session08.model.dto.ReaderCreateDTO;
import com.example.session08.model.entity.Reader;

public interface ReaderService {
    ApiResponse<Reader> createReader(ReaderCreateDTO request) throws ResourceConflictException, FileValidateException;
}
