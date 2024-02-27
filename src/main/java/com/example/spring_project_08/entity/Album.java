package com.example.spring_project_08.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Album {
    private int userId;
    private int id;
    private String title;
}
