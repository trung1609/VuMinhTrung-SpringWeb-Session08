package com.example.session08.service.impl;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl {

    private static final List<String> emails = Arrays.asList("a@gmail.com", "b@gmail.com");

    public boolean existsByEmail(String email) {
        return emails.contains(email);
    }
}
