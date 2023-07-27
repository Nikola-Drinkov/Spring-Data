package com.example.json_exercise.models.UserModels;

import com.example.json_exercise.models.ProductModels.ProductsDTO;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserProductsDTO {
    private String firstName;
    private String lastName;
    private Integer age;
    @SerializedName("soldProducts")
    private ProductsDTO sellingProducts;

    public UserProductsDTO(String firstName, String lastName, Integer age, ProductsDTO sellingProducts) {
        if(firstName==null) firstName = "null";
        else this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sellingProducts = sellingProducts;
    }
}
