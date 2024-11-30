package com.nk.learningplatformreactiveapi.dto;

import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class EnrollmentDTO {

    @NotNull(message = "Enrollment ID cannot be null")
    private Integer enrollmentId;

    @NotNull(message = "User ID cannot be null")
    private Integer userId;

    @NotNull(message = "Course ID cannot be null")
    private Integer courseId;

    // @PastOrPresent(message = "EnrolledAt must be a past or present date")
    private LocalDateTime enrolledAt;
}
