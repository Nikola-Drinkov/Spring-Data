package com.example.json_exercise.models.UserModels;

import com.example.json_exercise.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProductsWrapperDTO {
    private Integer usersCount;
    private List<UserProductsDTO> users;
}
