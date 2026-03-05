package com.example.session08.model.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CandidateCreateDTO {

    @NotBlank(message = "Full name cannot be blank")
    @Size(min = 5, max = 50, message = "Full name must be between 5 and 50 characters")
    private String fullName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;
    @NotNull(message = "Age cannot be blank")
    @Min(value = 18, message = "Age must be greater than or equal to 18")
    private Integer age;

    @NotNull(message = "Years of experience cannot be blank")
    @Min(value = 0, message = "Years of experience must be greater than or equal to 0")
    private Integer yearsOfExperience;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @NotBlank(message = "Bio cannot be blank")
    @Size(min = 10, max = 200, message = "Bio must be between 10 and 200 characters")
    private String bio;

    @NotBlank(message = "Phone cannot be blank")
    @Pattern(regexp = "^0([35789])[0-9]{8}$", message = "Phone number is invalid")
    private String phone;

    private MultipartFile file;
}
