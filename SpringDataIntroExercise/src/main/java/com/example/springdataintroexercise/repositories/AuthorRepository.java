package com.example.springdataintroexercise.repositories;

import com.example.springdataintroexercise.entitites.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query("SELECT a FROM Author a ORDER BY size(a.booksList) DESC")
    Set<Author> findAllOrderByBooksDesc();
}
