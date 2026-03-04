package com.example.session08.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
@Builder
public class ErrorResponseDTO {
    private int code;
    private String message;
    private Object details;
}
