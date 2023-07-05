package com.example.springdataintroexercise.services;

import com.example.springdataintroexercise.entitites.Category;
import com.example.springdataintroexercise.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean isDataSeeded() {
        return categoryRepository.count()>0;
    }

    @Override
    public void seedCategories(List<Category> categories) {
        categoryRepository.saveAllAndFlush(categories);
    }

    @Override
    public Category getRandomCategory() {
        final long count = this.categoryRepository.count();
        if (count != 0) {
            Integer categoryId = Math.toIntExact(new Random().nextLong(1L, count + 1L));
            return this.categoryRepository.findById(categoryId).orElseThrow(NoSuchElementException::new);
        }
        throw new RuntimeException();
    }
        @Override
        public Set<Category> getRandomCategories()  {
            Set<Category> categories = new HashSet<>();

            for (int i = 0; i < 1; i++) {
                categories.add(getRandomCategory());
            }
            return categories;
        }
    }