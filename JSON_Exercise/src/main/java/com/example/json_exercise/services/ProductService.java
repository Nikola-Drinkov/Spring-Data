package com.example.json_exercise.services;

import com.example.json_exercise.entities.Product;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<Product> getProductsWithPriceInRange(BigDecimal lowerBound, BigDecimal upperBound) throws IOException;
}
