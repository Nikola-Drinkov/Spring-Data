package com.example.json_exercise.models.ProductModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductsBasicInfoDTO {
    private String name;
    private BigDecimal price;
}
