package com.example.spring_project_08.service;

import com.example.spring_project_08.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> getBooks();
    Book getBookById(int id);
    Book saveBook(Book book);
    Book updateBookById(Book book, int id);
    String deleteBookById(int id);
}
