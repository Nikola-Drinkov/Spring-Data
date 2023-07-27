package com.example.json_exercise.repositories;

import com.example.json_exercise.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT * FROM `products_shop`.categories ORDER BY RAND() LIMIT 1;",nativeQuery = true)
    Category getRandomCategory();

    @Query("SELECT c FROM Category c ORDER BY (SELECT COUNT(p) FROM c.products p) DESC")
    List<Category> findAllOrderByProductsCount();

    @Query(value = "SELECT AVG(price) " +
            "FROM products_shop.products " +
            "JOIN products_shop.products_categories pc on products.id = pc.products_id " +
            "WHERE pc.categories_id = :categoryId", nativeQuery = true)
    BigDecimal findAveragePriceForCategory(Long categoryId);
    @Query(value = "SELECT SUM(price) " +
            "FROM products_shop.products " +
            "JOIN products_shop.products_categories pc on products.id = pc.products_id " +
            "WHERE pc.categories_id = :categoryId", nativeQuery = true)
    BigDecimal findSumPriceForCategory(Long categoryId);
}
