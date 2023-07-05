package com.example.springdataintroexercise;

import com.example.springdataintroexercise.services.AuthorService;
import com.example.springdataintroexercise.services.BookService;
import com.example.springdataintroexercise.services.SeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final BookService bookService;
    private AuthorService authorService;
    private final Scanner sc;

    public ConsoleRunner(SeedService seedService, BookService bookService, AuthorService authorService) {
        this.seedService = seedService;
        this.bookService = bookService;
        this.authorService = authorService;
        this.sc = new Scanner(System.in);
    }


    @Override
    public void run(String... args) throws Exception {

        //this.seedService.seedAllData();

        //1
        this.bookService.getAllBooksAfter2000();
        //2
        this.authorService.getAuthorsWithBooksBefore1990();
        //3
        this.authorService.getAuthorsOrderedByBooks();
        //4
        this.bookService.getAllBooksFromGeorgePowell();


    }
}
