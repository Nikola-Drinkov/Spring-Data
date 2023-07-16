package com.example.json_exercise.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Min;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity{
    @Column
    private String firstName;

    @Min(3)
    @Column(nullable = false)
    private String lastName;

    @Column
    private int age;

    @OneToMany(targetEntity = Product.class, mappedBy = "seller", fetch = FetchType.EAGER)
    private Set<Product> sellingProducts;

    @OneToMany(targetEntity = Product.class, mappedBy = "buyer", fetch = FetchType.EAGER)
    private Set<Product> boughtProducts;

    @ManyToMany
    private Set<User> friends;
}
