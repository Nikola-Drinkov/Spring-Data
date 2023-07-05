package com.example.springdataintroexercise.entitites;

import com.example.springdataintroexercise.enums.AgeRestriction;
import com.example.springdataintroexercise.enums.EditionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "books")
public class Book extends BaseEntity {
    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private EditionType editionType;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer copies;

    @Column
    private LocalDate releaseDate;

   @Enumerated(EnumType.STRING)
    private AgeRestriction ageRestriction;

   @ManyToMany
   @JoinTable(name = "books_categories", joinColumns = @JoinColumn(name = "book_id",referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "category_id",referencedColumnName = "id"))
   @JoinColumn(name = "author_id")
   private Set<Category> categoryList;

   @ManyToOne
    private Author author;
}
