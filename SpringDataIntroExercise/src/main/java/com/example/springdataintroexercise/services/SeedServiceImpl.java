package com.example.springdataintroexercise.services;

import com.example.springdataintroexercise.constants.File_Urls;
import com.example.springdataintroexercise.entitites.Author;
import com.example.springdataintroexercise.entitites.Book;
import com.example.springdataintroexercise.entitites.Category;
import com.example.springdataintroexercise.enums.AgeRestriction;
import com.example.springdataintroexercise.enums.EditionType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    private final AuthorService authorService;
    private final BookService bookService;
    private CategoryService categoryService;

    public SeedServiceImpl(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedAuthors() throws IOException {
        if(authorService.isDataSeeded()) return;

        this.authorService.seedAuthors(
                Files.readAllLines(Path.of(File_Urls.AUTHORS_URL))
                        .stream()
                        .map(firstAndLastName -> Author.builder()
                                .firstName(firstAndLastName.split(" ")[0])
                                .lastName(firstAndLastName.split(" ")[1])
                                .build()).collect(Collectors.toList())
        );
    }

    @Override
    public void seedBooks() throws IOException {
        if(this.bookService.isDataSeeded()) return;
        this.bookService.seedBooks(
                Files.readAllLines(Path.of(File_Urls.BOOKS_URL))
                        .stream()
                        .map(row->{
                            String[] parts = row.split(" ");
                            String title = Arrays.stream(parts)
                                    .skip(5)
                                    .collect(Collectors.joining(" "));

                            return Book.builder()
                                    .author(this.authorService.getRandomAuthor())
                                    .categoryList(this.categoryService.getRandomCategories())
                                    .title(title)
                                    .editionType(EditionType.values()[Integer.parseInt(parts[0])])
                                    .ageRestriction(AgeRestriction.values()[Integer.parseInt(parts[4])])
                                    .releaseDate(LocalDate.parse(parts[1], DateTimeFormatter.ofPattern("d/M/yyyy")))
                                    .copies(Integer.parseInt(parts[2]))
                                    .price(new BigDecimal(parts[3]))
                                    .build();
                        }).collect(Collectors.toList())
        );

    }

    @Override
    public void seedCategories() throws IOException {
        if(this.categoryService.isDataSeeded()) return;
        this.categoryService.seedCategories(
                Files.readAllLines(Path.of(File_Urls.CATEGORIES_URL))
                        .stream()
                        .filter(c->!c.isBlank())
                        .map(Category::new)
                        .collect(Collectors.toList())
        );
    }
}
