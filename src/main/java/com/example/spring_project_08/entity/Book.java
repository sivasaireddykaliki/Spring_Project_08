package com.example.spring_project_08.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Table(name="book")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    private String author;
    private String category;

}
