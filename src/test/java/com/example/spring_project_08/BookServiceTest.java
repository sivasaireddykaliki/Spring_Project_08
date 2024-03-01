package com.example.spring_project_08;

import com.example.spring_project_08.entity.Book;
import com.example.spring_project_08.helper.BookHelper;
import com.example.spring_project_08.repository.BookRepository;
import com.example.spring_project_08.service.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class BookServiceTest {

    // testing on BookServiceImpl class. InjectMocks on it
    @InjectMocks
    private BookServiceImpl bookService;

    // inside BookServiceImpl we use BookRepository object. so Mock on it
    @Mock
    private BookRepository bookRepository;

    private BookHelper bookHelper = new BookHelper();

    private List<Book> booksList;

    @BeforeEach
    public void setup(){

        booksList = new ArrayList<>();

        ReflectionTestUtils.setField(bookService,"bookRepository",bookRepository);

        Book book1=bookHelper.getDummyBook(1, "C++", "Ravi", "Tech");
        Book book2=bookHelper.getDummyBook(1, "C++", "Ravi", "Tech");
        Book book3=bookHelper.getDummyBook(1, "C++", "Ravi", "Tech");

        booksList.add(book1);
        booksList.add(book2);
        booksList.add(book3);
    }

/*
    @Test
    public void testGetBooks(){

        when(bookRepository.findAll()).thenReturn(booksList);

        List<Book> books = bookService.getBooks();

        Assertions.assertNotNull(books);
    }
*/


}
