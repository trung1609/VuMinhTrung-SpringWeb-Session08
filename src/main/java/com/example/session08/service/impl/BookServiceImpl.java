package com.example.session08.service.impl;

import com.example.session08.exception.FileStorageException;
import com.example.session08.exception.ResourceNotFoundException;
import com.example.session08.model.dto.ApiResponse;
import com.example.session08.model.dto.BookCreateDTO;
import com.example.session08.model.dto.BookUpdateDTO;
import com.example.session08.model.entity.Book;
import com.example.session08.repository.BookRepository;
import com.example.session08.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    @Override
    public ApiResponse<Book> createBook(BookCreateDTO book) throws FileStorageException {
        String imageUrl = null;

        MultipartFile coverImage = book.getCoverImage();

        if (coverImage == null || coverImage.isEmpty()) {
            return new ApiResponse<>("FAIL", "Cover image is required", null);
        }
        try {
            String fileName = coverImage.getOriginalFilename();
            imageUrl = UPLOAD_DIR + fileName;
            File file = new File(imageUrl);
            coverImage.transferTo(file);
        } catch (IOException e) {
            throw new FileStorageException("Failed to store file");
        }


        Book bookEntity = new Book();
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setStock(book.getStock());
        bookEntity.setCoverUrl(imageUrl);
        bookRepository.save(bookEntity);

        return new ApiResponse<>("SUCCESS", "Book created successfully", bookEntity);

    }

    @Override
    public ApiResponse<Book> updateBook(Long id, BookUpdateDTO request) throws ResourceNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        book.setStock(request.getStock());
        bookRepository.save(book);
        return new ApiResponse<>("SUCCESS", "Book updated successfully", book);
    }

    @Override
    public ApiResponse<Book> getBookById(Long id) throws ResourceNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return new ApiResponse<>("SUCCESS", "Book found successfully", book);
    }
}
