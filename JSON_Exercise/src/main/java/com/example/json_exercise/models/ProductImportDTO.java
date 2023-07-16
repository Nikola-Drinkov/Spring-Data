package com.example.json_exercise.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductImportDTO {
    private String name;
    private BigDecimal price;
}
