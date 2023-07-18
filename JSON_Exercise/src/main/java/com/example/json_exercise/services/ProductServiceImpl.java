package com.example.json_exercise.services;

import com.example.json_exercise.entities.Product;
import com.example.json_exercise.models.ProductModels.ProductsInRangeDTO;
import com.example.json_exercise.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.example.json_exercise.constants.Paths.TASK_1_PATH;
import static com.example.json_exercise.constants.Utils.*;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProductsWithPriceInRange(BigDecimal lowerBound, BigDecimal upperBound) throws IOException {
        List<Product> products = this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(lowerBound,upperBound);
        List<ProductsInRangeDTO> productsInRangeDTOS = new ArrayList<>();
            products.forEach(product -> {
                ProductsInRangeDTO productsInRangeDTO = new ProductsInRangeDTO(product.getName(),product.getPrice(),product.getSeller().getFirstName(),product.getSeller().getLastName());
                productsInRangeDTOS.add(productsInRangeDTO);
            });
        writeIntoJsonFile(productsInRangeDTOS, TASK_1_PATH);
        return products;
    }
}
