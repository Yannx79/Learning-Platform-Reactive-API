package com.nk.learningplatformreactiveapi.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RoleEnum {
    Student,
    Instructor,
    Admin,
}