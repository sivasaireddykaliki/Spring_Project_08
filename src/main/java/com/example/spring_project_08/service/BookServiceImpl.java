package com.example.spring_project_08.service;

import com.example.spring_project_08.entity.Book;
import com.example.spring_project_08.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Override
    public List<Book> getBooks() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

    @Override
    public Book getBookById(int id) {
        Optional<Book> book_ = bookRepository.findById(id);
        if(book_.isPresent())
        {
            Book book=book_.get();
            return book;
        }
        return null;
    }

    @Override
    public Book saveBook(Book book) {
        Book book_= bookRepository.save(book);
        return book_;
    }

    @Override
    public Book updateBookById(Book book, int id) {
        Optional<Book> book_ = bookRepository.findById(id);
        if(book_.isPresent())
        {
            Book savedBook=book_.get();
            savedBook.setAuthor(book.getAuthor());
            savedBook.setCategory(book.getCategory());
            savedBook.setTitle(book.getTitle());

            return bookRepository.save(savedBook);
        }
        return null;
    }

    @Override
    public String deleteBookById(int id) {
        Optional<Book> book_ = bookRepository.findById(id);
        if(book_.isPresent())
        {
            bookRepository.deleteById(id);
            return "Deleted Successfully";
        }

        return "Book not found";

    }
}
