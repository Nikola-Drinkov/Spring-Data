package com.example.springdataintroexercise.services;

import com.example.springdataintroexercise.entitites.Book;

import java.util.List;

public interface BookService {
    boolean isDataSeeded();

    void seedBooks(List<Book> books);

    List<Book> getAllBooksAfter2000();
    List<Book> getAllBooksBefore1990();

    List<Book> getAllBooksFromGeorgePowell();
}
