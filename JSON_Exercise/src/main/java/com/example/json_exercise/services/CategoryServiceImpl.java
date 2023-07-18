package com.example.json_exercise.services;

import com.example.json_exercise.entities.Category;
import com.example.json_exercise.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategoriesByProductsCount() {
        return null;
    }
}
