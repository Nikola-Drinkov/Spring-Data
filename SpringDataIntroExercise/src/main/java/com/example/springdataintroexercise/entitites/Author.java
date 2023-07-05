package com.example.springdataintroexercise.entitites;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "authors")
public class Author extends BaseEntity {
    @Column
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @OneToMany(targetEntity = Book.class, mappedBy = "author",fetch = FetchType.EAGER)
    private List<Book> booksList;
}
