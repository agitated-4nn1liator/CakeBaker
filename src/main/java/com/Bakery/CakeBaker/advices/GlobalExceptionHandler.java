package com.Bakery.CakeBaker.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
//    Handle Resource Not Found Exceptions
    @ExceptionHandler
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(NoSuchElementException ex) {
        ApiError error = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .build();
        return buildErrorResponseEntity(error);
    }

//    Handle Input Validation Exceptions
    @ExceptionHandler
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        ApiError error = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Validation failed")
                .errors(errors)
                .build();

        return buildErrorResponseEntity(error);
    }

//    Handle Internal Server Error Exceptions
    @ExceptionHandler
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception ex) {
        ApiError error = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ex.getMessage())
                .build();

        return buildErrorResponseEntity(error);
    }

//    Build error response entity
    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError error) {
        return new ResponseEntity<>(new ApiResponse<>(error), error.getStatus());
    }
}
