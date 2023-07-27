package com.example.json_exercise.models.ProductModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ProductsDTO {
    private Integer count;
    private Set<ProductsBasicInfoDTO> products;

}
