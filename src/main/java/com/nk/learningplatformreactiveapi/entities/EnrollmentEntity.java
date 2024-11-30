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
@Table(name = "enrollments")
public class EnrollmentEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(value = "EnrollmentID")
    private Integer enrollmentId;

    @Column(value = "UserID")
    private Integer userId;

    @Column(value = "CourseID")
    private Integer courseId;

    @Column(value = "EnrolledAt")
    private LocalDateTime enrolledAt;

}
