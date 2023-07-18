package com.example.json_exercise.services;

import com.example.json_exercise.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategoriesByProductsCount();
}
