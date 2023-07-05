package com.example.springdataintroexercise.entitites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BaseEntity{
    @Column
    private String name;

    @ManyToMany(targetEntity = Book.class, mappedBy = "categoryList")
    private List<Book> booksList;

    public Category(String s) {
        name = s;
    }
}
