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
@Table(name = "users")
public class UserEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(value = "UserId")
    private Integer userId;

    @Column(value = "FullName")
    private String fullName;

    @Column(value = "Email")
    private String email;

    @Column(value = "Password")
    private String password;

    @Column(value = "Role")
    private RoleEnum role;

    @Column(value = "CreatedAt")
    private LocalDateTime createdAt;

}
