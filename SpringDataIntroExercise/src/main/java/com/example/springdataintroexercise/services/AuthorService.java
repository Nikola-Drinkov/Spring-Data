package com.example.springdataintroexercise.services;

import com.example.springdataintroexercise.entitites.Author;

import java.util.List;
import java.util.Set;

public interface AuthorService {
    boolean isDataSeeded();
    void seedAuthors(List<Author> authors);
    Author getRandomAuthor();
    Set<Author> getAuthorsWithBooksBefore1990();
    Set<Author> getAuthorsOrderedByBooks();
    Set<Author> getAuthorsWhoseFirstNameEndsWithString(String end);
    void printAuthorsWithTotalCopiesOfBooks();
    void printAuthorWithTheirTotalBooks(String fullName);
}
