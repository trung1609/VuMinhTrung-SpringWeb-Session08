package com.example.session08.service.impl;

import com.example.session08.exception.BookAlreadyReturnedException;
import com.example.session08.exception.ResourceNotFoundException;
import com.example.session08.model.dto.ApiResponse;
import com.example.session08.model.dto.BorrowCreateDTO;
import com.example.session08.model.entity.Book;
import com.example.session08.model.entity.Borrow;
import com.example.session08.model.entity.BorrowStatus;
import com.example.session08.repository.BookRepository;
import com.example.session08.repository.BorrowRepository;
import com.example.session08.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public ApiResponse<Borrow> borrowBook(BorrowCreateDTO request) {
        Borrow borrow = new Borrow();
        borrow.setUsername(request.getUsername());
        borrow.setBookId(request.getBookId());
        borrow.setStatus(BorrowStatus.BORROWING);

        if (bookRepository.findById(request.getBookId()).isPresent()) {
            Book book = bookRepository.findById(request.getBookId()).get();
            book.setStock(book.getStock() - 1);
            bookRepository.save(book);
        }
        return new ApiResponse<>("SUCCESS", "Borrow book successfully", borrowRepository.save(borrow));
    }

    @Override
    public ApiResponse<Borrow> returnBook(Long ticketId) throws ResourceNotFoundException, BookAlreadyReturnedException {
        Borrow borrow = borrowRepository.findById(ticketId).orElseThrow(()-> new ResourceNotFoundException("Borrow ticket not found with id: " + ticketId));

        if(borrow.getStatus() == BorrowStatus.RETURNED){
            throw new BookAlreadyReturnedException("Book has already been returned");
        }
        borrow.setStatus(BorrowStatus.RETURNED);
        borrow.setReturnDate(LocalDate.now());
        if (bookRepository.findById(borrow.getBookId()).isPresent()) {
            Book book = bookRepository.findById(borrow.getBookId()).get();
            book.setStock(book.getStock() + 1);
            bookRepository.save(book);
        }

        borrowRepository.save(borrow);
        return new ApiResponse<>("SUCCESS", "Return book successfully", borrowRepository.save(borrow));
    }
}
