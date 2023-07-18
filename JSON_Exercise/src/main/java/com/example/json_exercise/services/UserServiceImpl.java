package com.example.json_exercise.services;

import com.example.json_exercise.entities.User;
import com.example.json_exercise.models.UserModels.UsersWithSoldProductsDTO;
import com.example.json_exercise.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.json_exercise.constants.Paths.TASK_2_PATH;
import static com.example.json_exercise.constants.Utils.MODEL_MAPPER;
import static com.example.json_exercise.constants.Utils.writeIntoJsonFile;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllBySoldProducts() throws IOException {
        List<User> users = this.userRepository.findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerLastName();
        List<UsersWithSoldProductsDTO> usersWithSoldProductsDTOS = new ArrayList<>();
        users.forEach(user -> {
            UsersWithSoldProductsDTO users1 = MODEL_MAPPER.map(user, UsersWithSoldProductsDTO.class);
            usersWithSoldProductsDTOS.add(users1);
        });
        writeIntoJsonFile(usersWithSoldProductsDTOS,TASK_2_PATH);
        return users;
    }
}
