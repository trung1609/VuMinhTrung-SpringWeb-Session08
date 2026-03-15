package com.example.session08.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ReaderCreateDTO {
    @Email
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Full name cannot be blank")
    private String fullName;

    @Pattern(regexp = "^0([35789])\\d{8}$", message = "Phone number is invalid")
    private String phoneNumber;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    private MultipartFile avatarFile;
}
