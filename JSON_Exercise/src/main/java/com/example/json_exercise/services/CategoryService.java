package com.example.json_exercise.services;

import com.example.json_exercise.entities.Category;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface CategoryService {
    List<Category> getAllCategoriesByProductsCount() throws IOException;
}
