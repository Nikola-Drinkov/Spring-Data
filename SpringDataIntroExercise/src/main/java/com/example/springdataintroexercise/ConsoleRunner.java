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

        //EXERCISE SPRING DATA INTRO
        //this.seedService.seedAllData();
        //1
        //this.bookService.getAllBooksAfter2000();
        //2
        //this.authorService.getAuthorsWithBooksBefore1990();
        //3
        //this.authorService.getAuthorsOrderedByBooks();
        //4
        //this.bookService.getAllBooksFromGeorgePowell();


        //EXERCISE ADVANCED QUERYING
        //1
        //this.bookService.getAllBooksByAgeRestriction(sc.nextLine());
        //2
        //this.bookService.getAllBooksByEditionTypeGoldAndLessThan5000Copies();
        //3
        //this.bookService.getAllBooksByPriceLessThan5OrPriceMoreThan40();
        //4
        //this.bookService.getAllBooksNotReleasedInYear(Integer.parseInt(sc.nextLine()));
        //5
        //this.bookService.getAllBooksBeforeDate(sc.nextLine());
        //6
        //this.authorService.getAuthorsWhoseFirstNameEndsWithString(sc.nextLine());
        //7
        //this.bookService.getAllBooksContainingString(sc.nextLine());
        //8
        //this.bookService.getAllBooksWithAuthorLastNameStartingWith(sc.nextLine());
        //9
        //this.bookService.getCountOfBooksWithTitleLongerThan(Integer.parseInt(sc.nextLine()));
        //10

    }
}
