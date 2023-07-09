package com.example.springdataintroexercise.repositories;

import com.example.springdataintroexercise.entitites.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query("SELECT a FROM Author a ORDER BY size(a.booksList) DESC")
    Set<Author> findAllOrderByBooksDesc();
    Set<Author> findAllByFirstNameEndingWith(String end);
    @Procedure(value = "usp_get_count_of_books_by_author")
    int findAllBooksByGivenAuthor(String fullName);
}
