package com.example.spring_project_08.repository;

import com.example.spring_project_08.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
