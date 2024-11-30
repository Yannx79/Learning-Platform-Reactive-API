package com.nk.learningplatformreactiveapi.exceptions;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiErrorResponse {

    private LocalDateTime timestamp;
    private String message;
    private String details;
    private String errorCode;

}
