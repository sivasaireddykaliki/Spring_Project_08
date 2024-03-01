package com.example.spring_project_08.helper;

import com.example.spring_project_08.entity.Book;

public class BookHelper {

    public Book getDummyBook(int id, String title, String author, String category)
    {
        Book book= Book.builder()
                .id(id)
                .title(title)
                .author(author)
                .category(category)
                .build();

        return book;

    }

    public String getUrl(String id)
    {
        String url="http://localhost:8080/api/books/";
        if(id==null)
        {
            return url;
        }
        else {
            return url+id;
        }
    }
}