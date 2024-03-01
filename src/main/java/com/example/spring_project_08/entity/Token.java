package com.example.spring_project_08.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tokens_info")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    @Id
    @GeneratedValue
    private int token_id;
    private String token_value;
    private boolean expired;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
