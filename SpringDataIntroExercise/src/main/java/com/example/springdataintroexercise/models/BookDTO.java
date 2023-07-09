package com.example.springdataintroexercise.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private String title;
    private String editionType;
    private String ageRestriction;
    private BigDecimal price;

    @Override
    public String toString() {
        return String.format("%s %s %s %s",title,editionType,ageRestriction,price);
    }
}
