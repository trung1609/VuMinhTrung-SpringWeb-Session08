package com.example.session08.service;

import com.example.session08.exception.FileStorageException;
import com.example.session08.model.dto.ApiResponse;
import com.example.session08.model.dto.BookCreateDTO;
import com.example.session08.model.entity.Book;

public interface BookService {
    ApiResponse<Book> createBook(BookCreateDTO book) throws FileStorageException;
}
