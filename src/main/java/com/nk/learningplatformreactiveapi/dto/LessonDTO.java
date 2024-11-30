package com.nk.learningplatformreactiveapi.dto;

import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class LessonDTO {

    @NotNull(message = "Lesson ID cannot be null")
    private Integer lessonId;

    @NotNull(message = "Course ID cannot be null")
    private Integer courseId;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    private String title;

    @NotBlank(message = "Content cannot be blank")
    private String content;

    @NotBlank(message = "Video URL cannot be blank")
    @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$", message = "Invalid URL format")
    private String videoUrl;

    // @PastOrPresent(message = "CreatedAt must be a past or present date")
    private LocalDateTime createdAt;
}

