package com.example.springdataautomapping.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
public class GameTitleAndPriceDTO {
    private String title;
    private BigDecimal price;

    @Override
    public String toString() {
        return title+" "+price;
    }
}

