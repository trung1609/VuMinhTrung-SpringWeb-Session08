package com.example.session08.service;

import com.cloudinary.Api;
import com.example.session08.exception.FileStorageException;
import com.example.session08.exception.ResourceNotFoundException;
import com.example.session08.model.dto.ApiResponse;
import com.example.session08.model.dto.BookCreateDTO;
import com.example.session08.model.dto.BookUpdateDTO;
import com.example.session08.model.entity.Book;

public interface BookService {
    ApiResponse<Book> createBook(BookCreateDTO book) throws FileStorageException;

    ApiResponse<Book> updateBook(Long id, BookUpdateDTO request) throws ResourceNotFoundException;

    ApiResponse<Book> getBookById(Long id) throws ResourceNotFoundException;
}
