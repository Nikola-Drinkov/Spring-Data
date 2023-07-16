package com.example.json_exercise.models.ProductModels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ProductsInRangeDTO {
    private String name;
    private BigDecimal price;
    private String seller;

    public ProductsInRangeDTO(String name, BigDecimal price, String firstName, String lastName) {
        this.name = name;
        this.price = price;
        String sellerName = "";
        if(firstName!=null) sellerName+=firstName+" ";
        sellerName+=lastName;
        this.seller = sellerName;
    }
}
