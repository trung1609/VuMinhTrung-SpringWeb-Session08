package com.example.session08.model.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookUpdateDTO {

    @Min(value = 0, message = "Stock must be greater than or equal to 0")
    private Integer stock;
}
