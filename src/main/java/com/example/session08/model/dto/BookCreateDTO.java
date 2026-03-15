package com.example.session08.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BookCreateDTO {
    private String title;
    private String author;
    private Integer stock;
    private MultipartFile coverImage;
}
