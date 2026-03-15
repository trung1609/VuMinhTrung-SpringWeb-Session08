package com.example.session08.controller;

import com.example.session08.exception.FileStorageException;
import com.example.session08.exception.ResourceNotFoundException;
import com.example.session08.model.dto.ApiResponse;
import com.example.session08.model.dto.BookCreateDTO;
import com.example.session08.model.dto.BookUpdateDTO;
import com.example.session08.model.entity.Book;
import com.example.session08.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<Book>> createBook(@ModelAttribute BookCreateDTO book) throws FileStorageException {
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Book>> updateBook(@PathVariable Long id, @Valid @RequestBody BookUpdateDTO request) throws ResourceNotFoundException {
        return new ResponseEntity<>(bookService.updateBook(id, request), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> getBookById(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }
}
