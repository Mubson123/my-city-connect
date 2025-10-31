package com.mc.extend.exception;

import com.mc.extend.model.ApiError;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class VisaControllerAdvice {
    public static final String MESSAGE = "message";

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
        Map<String, String> fieldErrors = new HashMap<>();
        fieldErrors.put(MESSAGE, ex.getMessage());
        ApiError apiError = new ApiError()
                .timestamp(OffsetDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Invalid request argument")
                .path(request.getDescription(false))
                .fieldErrors(fieldErrors);
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));
        ApiError apiError = new ApiError()
                .timestamp(OffsetDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Some fields are invalid")
                .path(request.getDescription(false))
                .fieldErrors(fieldErrors);
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(VisaNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(VisaNotFoundException ex, WebRequest request) {
        Map<String, String> fieldErrors = new HashMap<>();
        fieldErrors.put(MESSAGE, ex.getMessage());
        ApiError apiError = new ApiError()
                .timestamp(OffsetDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Resource not found")
                .path(request.getDescription(false))
                .fieldErrors(fieldErrors);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getConstraintViolations().forEach(error -> fieldErrors.put(error.getPropertyPath().toString(), error.getMessage()));
        ApiError apiError = new ApiError()
                .timestamp(OffsetDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error("Some fields are invalid")
                .path(request.getDescription(false))
                .fieldErrors(fieldErrors);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllUncaught(Exception ex, WebRequest request) {
        Map<String, String> fieldErrors = new HashMap<>();
        fieldErrors.put(MESSAGE, ex.getMessage());
        ApiError apiError = new ApiError()
                .timestamp(OffsetDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal server error")
                .path(request.getDescription(false))
                .fieldErrors(fieldErrors);
        return ResponseEntity.internalServerError().body(apiError);
    }
}
