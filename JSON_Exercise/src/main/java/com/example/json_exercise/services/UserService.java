package com.example.json_exercise.services;

import com.example.json_exercise.entities.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<User> getAllBySoldProducts() throws IOException;
    List<User> getAllWithAtLeastOneSold() throws IOException;
}
