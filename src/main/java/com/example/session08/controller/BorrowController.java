package com.example.session08.controller;

import com.example.session08.model.dto.ApiResponse;
import com.example.session08.model.dto.BorrowCreateDTO;
import com.example.session08.model.entity.Borrow;
import com.example.session08.service.BorrowService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/borrows")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping
    public ResponseEntity<ApiResponse<Borrow>> borrowBook(@Valid @RequestBody BorrowCreateDTO request){
        return new ResponseEntity<>(borrowService.borrowBook(request), HttpStatus.CREATED);
    }
}
