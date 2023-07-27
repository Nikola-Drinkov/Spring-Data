package com.example.json_exercise.models.CategoryModels;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryByProductsDTO {

            private String category;
            private int productCount;
            private BigDecimal averagePrice;
            private BigDecimal totalRevenue;
}
