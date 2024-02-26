package com.example.spring_project_08.controller;

import com.example.spring_project_08.entity.Book;
import com.example.spring_project_08.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){
        List<Book> books = bookService.getBooks();
        if(books == null)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBook(@PathVariable int id){
        Book book = bookService.getBookById(id);
        if(book == null)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
    }

    @PostMapping("/books")
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        Book book_ = bookService.saveBook(book);
        if(book_ == null)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity<>(book_, HttpStatus.OK);
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBookById(@RequestBody Book book,@PathVariable int id){
        Book updatedBook = bookService.updateBookById(book,id);
        if(updatedBook == null)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable int id){
        String result = bookService.deleteBookById(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}
