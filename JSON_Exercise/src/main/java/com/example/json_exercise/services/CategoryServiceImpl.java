package com.example.json_exercise.services;

import com.example.json_exercise.entities.Category;
import com.example.json_exercise.entities.Product;

import static com.example.json_exercise.constants.Paths.TASK_3_PATH;
import static com.example.json_exercise.constants.Utils.*;
import com.example.json_exercise.models.CategoryModels.CategoryByProductsDTO;
import com.example.json_exercise.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;


    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategoriesByProductsCount() throws IOException {
        List<Category> categories = this.categoryRepository.findAllOrderByProductsCount();
        List<CategoryByProductsDTO> categoryByProductsDTOS = new ArrayList<>();
        categories.forEach(c->{
            BigDecimal average = this.categoryRepository.findAveragePriceForCategory(c.getId());
            BigDecimal sum = this.categoryRepository.findSumPriceForCategory(c.getId());
            CategoryByProductsDTO categoryDTO = new CategoryByProductsDTO(c.getName(), c.getProducts().size(), average, sum);
            categoryByProductsDTOS.add(categoryDTO);
        });
        writeIntoJsonFile(categoryByProductsDTOS, TASK_3_PATH);
        return categories;
    }
}
