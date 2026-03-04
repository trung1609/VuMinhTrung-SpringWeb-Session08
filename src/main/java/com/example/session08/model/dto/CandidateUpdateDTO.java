package com.example.session08.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateUpdateDTO {

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @NotBlank(message = "Bio cannot be blank")
    @Size(min = 10, max = 200, message = "Bio must be between 10 and 200 characters")
    private String bio;
}
