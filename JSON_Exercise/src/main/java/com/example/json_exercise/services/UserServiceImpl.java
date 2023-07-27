package com.example.json_exercise.services;

import com.example.json_exercise.entities.Product;
import com.example.json_exercise.entities.User;
import com.example.json_exercise.models.ProductModels.ProductsBasicInfoDTO;
import com.example.json_exercise.models.ProductModels.ProductsDTO;
import com.example.json_exercise.models.UserModels.UserProductsDTO;
import com.example.json_exercise.models.UserModels.UserProductsWrapperDTO;
import com.example.json_exercise.models.UserModels.UsersWithSoldProductsDTO;
import com.example.json_exercise.repositories.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.json_exercise.constants.Paths.TASK_2_PATH;
import static com.example.json_exercise.constants.Paths.TASK_4_PATH;
import static com.example.json_exercise.constants.Utils.MODEL_MAPPER;
import static com.example.json_exercise.constants.Utils.writeIntoJsonFile;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Gson gson;

    public UserServiceImpl(UserRepository userRepository, Gson gson) {
        this.userRepository = userRepository;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
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

    @Override
    public List<User> getAllWithAtLeastOneSold() throws IOException {
        List<User> users = this.userRepository.findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerLastName();
        List<UserProductsDTO> userProductsDTOS = new ArrayList<>();

        users.forEach(u->{
            Set<Product> products = u.getSellingProducts();
            Set<ProductsBasicInfoDTO> productsBasicInfoDTOS = new HashSet<>();
            products.forEach(p->{
                ProductsBasicInfoDTO productsBasicInfoDTO = new ProductsBasicInfoDTO(p.getName(), p.getPrice());
                productsBasicInfoDTOS.add(productsBasicInfoDTO);
            });
            ProductsDTO productsDTO = new ProductsDTO(productsBasicInfoDTOS.size(), productsBasicInfoDTOS);

            UserProductsDTO userProductsDTO = new UserProductsDTO(u.getFirstName(),u.getLastName(),u.getAge(),productsDTO);
            userProductsDTOS.add(userProductsDTO);
        });
        UserProductsWrapperDTO userProductsWrapperDTO = new UserProductsWrapperDTO(users.size(), userProductsDTOS);

        FileWriter fileWriter = new FileWriter(TASK_4_PATH.toFile());
        gson.toJson(userProductsWrapperDTO, fileWriter);
        fileWriter.flush();
        fileWriter.close();

        return users;
    }
}
