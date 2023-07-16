package com.example.json_exercise.repositories;

import com.example.json_exercise.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM `products_shop`.users ORDER BY RAND() LIMIT 1;",nativeQuery = true)
    User getRandomUser();
}
