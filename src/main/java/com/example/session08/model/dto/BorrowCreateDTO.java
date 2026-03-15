package com.example.session08.model.dto;

import com.example.session08.validate.ExistingBookId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowCreateDTO {
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @ExistingBookId
    @NotNull(message = "Book id cannot be null")
    private Long bookId;
}
