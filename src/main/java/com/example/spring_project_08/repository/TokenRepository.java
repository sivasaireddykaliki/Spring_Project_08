package com.example.spring_project_08.repository;

import com.example.spring_project_08.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer> {
}
