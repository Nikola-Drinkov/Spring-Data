package com.example.springdataintroexercise.services;

import com.example.springdataintroexercise.entitites.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    boolean isDataSeeded();
    void seedCategories(List<Category> categories);

    Category getRandomCategory();
    Set<Category> getRandomCategories();
}
