package com.nk.learningplatformreactiveapi.dto;

import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class QuizDTO {

    @NotNull(message = "Quiz ID cannot be null")
    private Integer quizId;

    @NotNull(message = "Course ID cannot be null")
    private Integer courseId;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    private String title;

    // @PastOrPresent(message = "CreatedAt must be a past or present date")
    private LocalDateTime createdAt;
}
