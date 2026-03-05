package com.example.session08.service.impl;

import com.example.session08.model.dto.ApiResponse;
import com.example.session08.model.dto.BorrowCreateDTO;
import com.example.session08.model.entity.Borrow;
import com.example.session08.repository.BorrowRepository;
import com.example.session08.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    @Override
    public ApiResponse<Borrow> borrowBook(BorrowCreateDTO request) {
        Borrow borrow = new Borrow();
        borrow.setUsername(request.getUsername());
        borrow.setBookId(request.getBookId());
        return new ApiResponse<>("SUCCESS", "Borrow book successfully", borrowRepository.save(borrow));
    }
}
