package com.example.spring_project_08.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangePassword {
    String currentPassword;
    String newPassword;
    String confirmPassword;
}
