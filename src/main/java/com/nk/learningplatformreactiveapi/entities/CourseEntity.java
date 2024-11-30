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
@Table(name = "courses")
public class CourseEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(value = "CourseID")
    private Integer courseId;

    @Column(value = "Title")
    private String title;

    @Column(value = "Description")
    private String description;

    @Column(value = "InstructorID")
    private Integer instructorId;

    @Column(value = "CreatedAt")
    private LocalDateTime createdAt;

}
