package com.example.springdataintroexercise.repositories;

import com.example.springdataintroexercise.entitites.Book;
import com.example.springdataintroexercise.enums.AgeRestriction;
import com.example.springdataintroexercise.enums.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByReleaseDateAfter(LocalDate date);
    List<Book> findAllByReleaseDateBefore(LocalDate date);
    List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstName, String LastName);
    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);
    List<Book> findAllByEditionTypeAndCopiesIsLessThan(EditionType editionType, int copies);
    List<Book> findAllByPriceIsLessThanOrPriceIsGreaterThan(BigDecimal lowerThan, BigDecimal moreThan);
    @Query("SELECT b FROM Book b WHERE YEAR(b.releaseDate) != :year")
    List<Book> findBooksNotReleasedInYear(int year);
    List<Book> findAllByTitleContaining(String containing);
    List<Book> findAllByAuthorLastNameStartsWith(String start);
    @Query("SELECT b FROM Book b WHERE LENGTH(b.title) > :length")
    List<Book> findAllByTitleLengthGreaterThan(int length);

}
