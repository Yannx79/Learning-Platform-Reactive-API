package com.nk.learningplatformreactiveapi.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "lessons")
public class LessonEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(value = "LessonID")
    private Integer lessonId;

    @Column(value = "CourseID")
    private Integer courseId;

    @Column(value = "Title")
    private String title;

    @Column(value = "Content")
    private String content;

    @Column(value = "VideoURL")
    private String videoUrl;

    @Column(value = "CreatedAt")
    private LocalDateTime createdAt;
}