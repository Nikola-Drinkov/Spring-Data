package com.example.springdataintroexercise.services;

import com.example.springdataintroexercise.entitites.Book;
import com.example.springdataintroexercise.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean isDataSeeded() {
        return bookRepository.count()>0;
    }

    @Override
    public void seedBooks(List<Book> books) {
        this.bookRepository.saveAllAndFlush(books);
    }

    @Override
    public List<Book> getAllBooksAfter2000() {
        List<Book> books =  this.bookRepository.findAllByReleaseDateAfter(LocalDate.of(2000,1,1));
        books.forEach(b-> System.out.println(b.getTitle()));
        return books;
    }

    @Override
    public List<Book> getAllBooksBefore1990() {
        return this.bookRepository.findAllByReleaseDateBefore(LocalDate.of(1990, 1, 1));
    }

    @Override
    public List<Book> getAllBooksFromGeorgePowell() {
        List<Book> books = this.bookRepository.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc("George", "Powell");
        books.forEach(b-> System.out.println(b.getTitle()+" "+b.getReleaseDate()+" "+b.getCopies()));
        return books;
    }

}
