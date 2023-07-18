package com.example.json_exercise;

import com.example.json_exercise.services.ProductService;
import com.example.json_exercise.services.SeedService;
import com.example.json_exercise.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final ProductService productService;
    private final UserService userService;

    public ConsoleRunner(SeedService seedService, ProductService productService, UserService userService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedService.seedAll();
        this.productService.getProductsWithPriceInRange(BigDecimal.valueOf(500L),BigDecimal.valueOf(1000L));
        this.userService.getAllBySoldProducts();
    }
}
