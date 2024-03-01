package com.example.spring_project_08;

import com.example.spring_project_08.entity.Book;
import com.example.spring_project_08.helper.BookHelper;
import com.example.spring_project_08.repository.BookRepository;
import com.example.spring_project_08.service.BookService;

import com.example.spring_project_08.service.BookServiceImpl;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookServiceTest {



    // which service we want to test
    @InjectMocks
    private BookServiceImpl bookService;

    // declare dependency of bookservice. Dependencies are mocked
    @Mock
    private BookRepository bookRepository;

    private BookHelper bookHelper;

    // we should tell mocking to inject dependencies to BookServiceImpl. We use @InjectMocks. Find dependncies with @Mock annotation.


    // Next we should tell mockito framework that we want to start mocking operations from current test class (BookServiceTest)

    /*
     In @BeforeEach method we use MockitoAnnotations.openMocks(this) method. This refer object or instance of BookServiceTest
     */

    private List<Book> booksList;



    @BeforeEach
    public void setup(){

        MockitoAnnotations.openMocks(this); // important method. Instantiates objects with @Mocks and @InjectMocks


        booksList = new ArrayList<>();

        bookHelper=new BookHelper();

        Book book1=bookHelper.getDummyBook(1, "C++", "Ravi", "Tech");
        Book book2=bookHelper.getDummyBook(2, "Java", "Rahul", "Tech");
        Book book3=bookHelper.getDummyBook(3, "Python", "Ramesh", "Tech");

        booksList.add(book1);
        booksList.add(book2);
        booksList.add(book3);
    }


    @Test
    public void testGetBooks(){

        when(bookRepository.findAll()).thenReturn(booksList);

        List<Book> books = bookService.getBooks();

        Assertions.assertNotNull(books);

    }

    @Test
    public void testGetBookById(){

        int id=1;

        when(bookRepository.findById(id)).thenReturn(booksList.stream().filter(book -> book.getId()==id).findFirst());

        Book book = bookService.getBookById(id);

        Assertions.assertNotNull(book);

    }

    @Test
    public void testSaveBook(){

        Book newBook= Book.builder().id(4).author("Rajesh").category("Tech").title("C#").build();

        when(bookRepository.save(newBook)).thenReturn(newBook);

        Book book = bookService.saveBook(newBook);

        Assertions.assertNotNull(book);

    }

    @Test
    public void testUpdateBookById(){
        Book updated_book = Book.builder().id(1).author("Rajesh").category("Tech").title("C#").build();

        int id = updated_book.getId();


        when(bookRepository.findById(id)).thenReturn(booksList.stream().filter(book -> book.getId()==id).findFirst());
        when(bookRepository.save(updated_book)).thenReturn(updated_book);

        Book book=bookService.updateBookById(updated_book,id);


        Assertions.assertNotNull(book);
    }

    @Test
    public void testDeleteById(){

        // if book found, its deleted. If book not found or null, deletion fails

        int id=1;

        when(bookRepository.findById(id)).thenReturn(booksList.stream().filter(book -> book.getId()==id).findFirst());

        Book book = bookService.getBookById(id);

        Assertions.assertNotNull(book,"Book not found");

    }


}
