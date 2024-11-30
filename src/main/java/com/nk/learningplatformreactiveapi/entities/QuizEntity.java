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
@Table(name = "quizzes")
public class QuizEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(value = "QuizID")
    private Integer quizId;

    @Column(value = "CourseID")
    private Integer courseId;

    @Column(value = "Title")
    private String title;

    @Column(value = "CreatedAt")
    private LocalDateTime createdAt;

}
