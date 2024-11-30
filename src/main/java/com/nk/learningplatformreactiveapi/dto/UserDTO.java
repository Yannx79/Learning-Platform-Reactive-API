package com.nk.learningplatformreactiveapi.dto;

import com.nk.learningplatformreactiveapi.entities.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer userId;

    @NotBlank(message = "The full name is obligatory.")
    @Size(max = 100, message = "The full name must not exceed 100 characters.")
    private String fullName;

    @NotBlank(message = "The email is obligatory.")
    @Email(message = "The email should be valid.")
    @Size(max = 100, message = "The email should not exceed 100 characters.")
    private String email;

    @NotBlank(message = "The password is obligatory.")
    @Size(min = 8, max = 255, message = "The password must be between 8 and 255 characters.")
    private String password;

    @NotNull(message = "The rol is obligatory.")
    private RoleEnum role;

    private LocalDateTime createdAt;

}
