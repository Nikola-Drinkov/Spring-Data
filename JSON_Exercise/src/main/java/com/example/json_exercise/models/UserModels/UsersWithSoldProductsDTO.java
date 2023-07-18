package com.example.json_exercise.models.UserModels;

import com.example.json_exercise.models.ProductModels.ProductSoldModel;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UsersWithSoldProductsDTO {
    private String firstName;
    private String lastName;
    @SerializedName(value = "soldProducts")
    private Set<ProductSoldModel> sellingProducts;
}
