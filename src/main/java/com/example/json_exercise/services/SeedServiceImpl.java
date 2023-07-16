package com.example.json_exercise.services;

import com.example.json_exercise.entities.Category;
import com.example.json_exercise.entities.Product;
import com.example.json_exercise.entities.User;
import com.example.json_exercise.models.CategoryImportDTO;
import com.example.json_exercise.models.ProductImportDTO;
import com.example.json_exercise.models.UserImportDTO;
import com.example.json_exercise.repositories.CategoryRepository;
import com.example.json_exercise.repositories.ProductRepository;
import com.example.json_exercise.repositories.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.json_exercise.constants.Paths.*;
import static com.example.json_exercise.constants.Utils.GSON;
import static com.example.json_exercise.constants.Utils.MODEL_MAPPER;
import static java.util.stream.Collectors.toList;

@Service
public class SeedServiceImpl implements SeedService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public SeedServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void seedUsers() throws IOException {
        if (this.userRepository.count()>0) return;

        FileReader fileReader = new FileReader(USERS_JSON_PATH.toFile());
        List<User> users = Arrays.stream(GSON.fromJson(fileReader, UserImportDTO[].class))
                .map(userImportDTO -> MODEL_MAPPER.map(userImportDTO,User.class))
                .toList();
        fileReader.close();
        this.userRepository.saveAllAndFlush(users);

    }

    @Override
    public void seedCategories() throws IOException {
        if(this.categoryRepository.count()>0) return;

        final FileReader fileReader = new FileReader(CATEGORIES_JSON_PATH.toFile());
        List<Category> categories = Arrays.stream(GSON.fromJson(fileReader, CategoryImportDTO[].class))
                .map(categoryImportDTO -> MODEL_MAPPER.map(categoryImportDTO, Category.class))
                .toList();
        fileReader.close();
        this.categoryRepository.saveAllAndFlush(categories);
    }

    @Override
    public void seedProducts() throws IOException {
        if(this.productRepository.count()>0) return;

        final FileReader fileReader = new FileReader(PRODUCTS_JSON_PATH.toFile());
        List<Product> products = Arrays.stream(GSON.fromJson(fileReader, ProductImportDTO[].class))
                .map(productImportDTO -> MODEL_MAPPER.map(productImportDTO, Product.class))
                .map(this::setRandomCategories)
                .map(this::setRandomSeller)
                .map(this::setRandomBuyer)
                .toList();
        fileReader.close();
        this.productRepository.saveAllAndFlush(products);
    }
    private Product setRandomCategories(Product product){
        final Random random = new Random();
        int countOfCategories = random.nextInt((int) this.categoryRepository.count());

        Set<Category> categories = new HashSet<>();
        IntStream.range(0, countOfCategories+1)
                .forEach(value -> {
                    categories.add(this.categoryRepository.getRandomCategory());
                });
        product.setCategories(categories);
        return product;
    }
    private Product setRandomSeller(Product product){
        product.setSeller(this.userRepository.getRandomUser());
        return product;
    }
    private Product setRandomBuyer(Product product){
        if(product.getPrice().compareTo(BigDecimal.valueOf(750L))>0) {
            product.setBuyer(this.userRepository.getRandomUser());
            if(product.getBuyer().equals(product.getSeller())){
                product.setBuyer(null);
            }
        }
        return product;
    }
}
