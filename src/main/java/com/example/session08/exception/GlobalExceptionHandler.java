package com.example.session08.exception;

import com.example.session08.model.dto.ApiResponse;
import com.example.session08.model.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //Lỗi người dùng: 400
    // Lỗi DB: 409 , 415, 422 ...
    // Lỗi server: 500


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorResponseDTO response = ErrorResponseDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed")
                .details(errors)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundCandidateException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<String> handleNotFoundCandidateException(NotFoundCandidateException ex) {
        return new ApiResponse<>("FAIL", ex.getMessage(), null);
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<?> handleResourceConflictException(ResourceConflictException ex) {
        ErrorResponseDTO response = ErrorResponseDTO.builder()
                .code(HttpStatus.CONFLICT.value())
                .message("Validation failed")
                .details("Resource conflict: " + ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<?> handleFileStorageException(FileStorageException ex) {
        ErrorResponseDTO response = ErrorResponseDTO.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Validation failed")
                .details("File storage error: " + ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        ErrorResponseDTO response = ErrorResponseDTO.builder()
                .code(HttpStatus.PAYLOAD_TOO_LARGE.value())
                .message("Validation failed")
                .details("File size exceeds the maximum allowed size ")
                .build();
        return new ResponseEntity<>(response, HttpStatus.PAYLOAD_TOO_LARGE);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponseDTO response = ErrorResponseDTO.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message("Resource not found")
                .details(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex){
        ErrorResponseDTO response = ErrorResponseDTO.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Internal server error")
                .details(ex.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }





}
