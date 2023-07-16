package com.example.springdataautomapping.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DetailedGameDTO {
    private String title;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;

    @Override
    public String toString() {
        return String.format("Title: %s%n" +
                "Price: %s%n" +
                "Description: %s%n" +
                "Release date: %s",title,price,description,releaseDate);
    }
}
