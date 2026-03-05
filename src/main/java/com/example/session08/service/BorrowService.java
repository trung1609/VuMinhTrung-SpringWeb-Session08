package com.example.session08.service;

import com.example.session08.exception.BookAlreadyReturnedException;
import com.example.session08.exception.ResourceNotFoundException;
import com.example.session08.model.dto.ApiResponse;
import com.example.session08.model.dto.BorrowCreateDTO;
import com.example.session08.model.entity.Borrow;

public interface BorrowService {

    ApiResponse<Borrow> borrowBook(BorrowCreateDTO request);
    ApiResponse<Borrow> returnBook(Long ticketId) throws ResourceNotFoundException, BookAlreadyReturnedException;
}
