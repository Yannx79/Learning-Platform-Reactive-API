package com.nk.learningplatformreactiveapi.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.util.stream.Collectors;

// @RestControllerAdvice
// @ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /*@ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalStateException(IllegalStateException ex, WebRequest req) {
        ApiErrorResponse error = ErrorUtils.buildErrorResponse(ex, req, HttpStatus.BAD_REQUEST, "ILLEGAL_STATE");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }*/

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleAllExceptions(Exception ex, WebRequest req) {
        ApiErrorResponse errorResponse = ErrorUtils.buildErrorResponse(ex, req, HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleModelNotFoundException(EntityNotFoundException ex, WebRequest req) {
        ApiErrorResponse errorResponse = ErrorUtils.buildErrorResponse(ex, req, HttpStatus.NOT_FOUND, "NOT_FOUND");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ApiErrorResponse> handleSQLException(SQLException ex, WebRequest req) {
        ApiErrorResponse errorResponse = ErrorUtils.buildErrorResponse(ex, req, HttpStatus.CONFLICT, "DATABASE_ERROR");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest req) {
        String validationErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ApiErrorResponse errorResponse = ErrorUtils.buildValidationErrorResponse(validationErrors, req);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
