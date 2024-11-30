package com.nk.learningplatformreactiveapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

public class ErrorUtils {

    public static ApiErrorResponse buildErrorResponse(Exception ex, WebRequest req, HttpStatus status, String errorCode) {
        return new ApiErrorResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                req.getDescription(false),
                errorCode
        );
    }

    public static ApiErrorResponse buildValidationErrorResponse(String message, WebRequest req) {
        return new ApiErrorResponse(
                LocalDateTime.now(),
                message,
                req.getDescription(false),
                "VALIDATION_ERROR"
        );
    }

}
