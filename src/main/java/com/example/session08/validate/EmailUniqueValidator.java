package com.example.session08.validate;

import com.example.session08.service.impl.UserServiceImpl;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailUniqueValidator implements ConstraintValidator<EmailUnique, String> {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !userService.existsByEmail(value);
    }
}
