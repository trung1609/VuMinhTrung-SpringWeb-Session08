package com.example.session08.controller;

import com.example.session08.model.dto.ErrorResponseDTO;
import com.example.session08.model.dto.UserDtoRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/users")
public class UserController {

    @PostMapping
    public ResponseEntity<?> postUser(@Valid @RequestBody UserDtoRequest request, BindingResult result){

        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }
}
