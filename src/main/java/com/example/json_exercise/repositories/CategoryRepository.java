package com.example.json_exercise.repositories;

import com.example.json_exercise.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT * FROM `products_shop`.categories ORDER BY RAND() LIMIT 1;",nativeQuery = true)
    Category getRandomCategory();
}
