package com.example.json_exercise.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity{
    @Min(3)
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    private User buyer;

    @ManyToOne(fetch = FetchType.EAGER)
    private User seller;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Category> categories;
}
