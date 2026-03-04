package com.example.session08.model.dto;

import com.example.session08.validate.EmailUnique;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class UserDtoRequest {

    @NotNull( message = "User id cannot be null")
    @Pattern(regexp = "^U[0-9]{3}$")
    private String userId;

    @NotBlank( message = "Username cannot be blank")
    @Size(min = 6, max = 30, message = "Username must be between 6 and 30 characters")
    private String username;

    @NotNull( message = "Password cannot be null")
    @Pattern(regexp = "^[a-zA-Z\\w\\D][a-zA-Z0-9\\w][a-zA-Z0-9].$")
    private String password;

    @NotNull( message = "Birth date cannot be null")
    @Past
    private LocalDate birthDate;

    @Email( message = "Invalid email format")
    @EmailUnique
    private String email;


    private String phone;

    @Min(value = 0, message = "Height must be greater than or equal to 0")
    @Max(value = 3, message = "Height must be less than or equal to 3")
    private BigDecimal height;

    @NotNull( message = "Number serial cannot be null")
    @Positive(message = "Number serial must be greater than 0")
    private Integer numberSerial;
}
