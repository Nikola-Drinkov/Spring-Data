package com.example.springdataintroexercise.services;

import com.example.springdataintroexercise.entitites.Author;
import com.example.springdataintroexercise.entitites.Book;
import com.example.springdataintroexercise.enums.AgeRestriction;
import com.example.springdataintroexercise.enums.EditionType;
import com.example.springdataintroexercise.models.BookDTO;
import com.example.springdataintroexercise.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    public List<Book> getAllBooksByAgeRestriction(String ageRestriction) {
        List<Book> books  = this.bookRepository.findAllByAgeRestriction(AgeRestriction.valueOf(ageRestriction.toUpperCase()));
        books.forEach(b-> System.out.println(b.getTitle()));
        return books;
    }

    @Override
    public List<Book> getAllBooksByEditionTypeGoldAndLessThan5000Copies() {
        List<Book> books = this.bookRepository.findAllByEditionTypeAndCopiesIsLessThan(EditionType.GOLD, 5000);
        books.forEach(b-> System.out.println(b.getTitle()));
        return books;
    }

    @Override
    public List<Book> getAllBooksByPriceLessThan5OrPriceMoreThan40() {
        List<Book> books = this.bookRepository.findAllByPriceIsLessThanOrPriceIsGreaterThan(BigDecimal.valueOf(5),BigDecimal.valueOf(40));
        books.forEach(b-> System.out.printf("%s - $%s%n", b.getTitle(),b.getPrice()));
        return books;
    }

    @Override
    public List<Book> getAllBooksNotReleasedInYear(int year) {
        List<Book> books = this.bookRepository.findBooksNotReleasedInYear(year);
        books.forEach(b-> System.out.println(b.getTitle()));
        return books;
    }

    @Override
    public List<Book> getAllBooksBeforeDate(String date) {
        int[] dateParts = Arrays.stream(date.split("-")).mapToInt(Integer::parseInt).toArray();
        LocalDate localDate = LocalDate.of(dateParts[2],dateParts[1],dateParts[0]);
        List<Book> books = this.bookRepository.findAllByReleaseDateBefore(localDate);
        books.forEach(b-> System.out.printf("%s %s %s%n",b.getTitle(),b.getEditionType(),b.getPrice()));
        return books;
    }

    @Override
    public List<Book> getAllBooksContainingString(String containing) {
        List<Book> books = this.bookRepository.findAllByTitleContaining(containing);
        books.forEach(b-> System.out.println(b.getTitle()));
        return books;
    }

    @Override
    public List<Book> getAllBooksWithAuthorLastNameStartingWith(String start) {
        List<Book> books = this.bookRepository.findAllByAuthorLastNameStartsWith(start);
        books.forEach(b-> System.out.printf("%s (%s %s)%n",b.getTitle(),b.getAuthor().getFirstName(),b.getAuthor().getLastName()));
        return books;
    }

    @Override
    public int getCountOfBooksWithTitleLongerThan(int length) {
        int count = this.bookRepository.findAllByTitleLengthGreaterThan(length).size();
        System.out.println(count);
        return count;
    }

    @Override
    public int getTotalCopiesOfBooksForAuthor(Author author) {
        return this.bookRepository.findTotalCopiesOfBooksForAuthor(author);
    }

    @Override
    public BookDTO getBookDTO(String title) {
        Book book = this.bookRepository.findByTitle(title);
        BookDTO bookDTO = new BookDTO(book.getTitle(),book.getEditionType().toString(),book.getAgeRestriction().toString(),book.getPrice());
        System.out.println(bookDTO);
        return bookDTO;
    }
}
