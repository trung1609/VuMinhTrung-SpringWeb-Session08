package com.example.session08.validate;

import com.example.session08.repository.BookRepository;
import com.example.session08.repository.BorrowRepository;
import com.example.session08.service.BookService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class BookIdValidator implements ConstraintValidator<ExistingBookId, Long> {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return bookRepository.existsById(value);
    }
}
