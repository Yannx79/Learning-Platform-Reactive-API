package com.nk.learningplatformreactiveapi.dto;

import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    @NotNull(message = "Course ID cannot be null")
    private Integer courseId;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotNull(message = "Instructor ID cannot be null")
    private Integer instructorId;

    // @PastOrPresent(message = "CreatedAt must be a past or present date")
    private LocalDateTime createdAt;
}
