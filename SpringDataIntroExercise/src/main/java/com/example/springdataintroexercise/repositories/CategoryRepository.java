package com.example.springdataintroexercise.repositories;

import com.example.springdataintroexercise.entitites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
