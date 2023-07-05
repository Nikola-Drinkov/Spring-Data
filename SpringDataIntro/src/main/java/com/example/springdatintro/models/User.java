package com.example.springdatintro.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Basic
    private Integer age;

    public User(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;

}
