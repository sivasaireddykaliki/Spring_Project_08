package com.example.spring_project_08.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private Role role;
}
