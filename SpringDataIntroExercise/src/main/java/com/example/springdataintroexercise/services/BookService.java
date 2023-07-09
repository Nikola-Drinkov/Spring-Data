package com.example.springdataintroexercise.services;

import com.example.springdataintroexercise.entitites.Author;
import com.example.springdataintroexercise.entitites.Book;
import com.example.springdataintroexercise.models.BookDTO;

import java.util.List;

public interface BookService {
    boolean isDataSeeded();

    void seedBooks(List<Book> books);
    List<Book> getAllBooksAfter2000();
    List<Book> getAllBooksBefore1990();
    List<Book> getAllBooksFromGeorgePowell();
    List<Book> getAllBooksByAgeRestriction(String ageRestriction);
    List<Book> getAllBooksByEditionTypeGoldAndLessThan5000Copies();
    List<Book> getAllBooksByPriceLessThan5OrPriceMoreThan40();
    List<Book> getAllBooksNotReleasedInYear(int year);
    List<Book> getAllBooksBeforeDate(String date);
    List<Book> getAllBooksContainingString(String containing);
    List<Book> getAllBooksWithAuthorLastNameStartingWith(String start);
    int getCountOfBooksWithTitleLongerThan(int length);
    int getTotalCopiesOfBooksForAuthor(Author author);
    BookDTO getBookDTO(String title);
    void increaseCopiesForBooksAfterDate(String date, int copies);
    void removeBooksWithCopiesLessThan(int copies);
}
