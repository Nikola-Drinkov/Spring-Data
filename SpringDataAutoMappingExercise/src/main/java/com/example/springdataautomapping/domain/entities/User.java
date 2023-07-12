package com.example.springdataautomapping.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private Boolean isAdmin;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    Set<Game> games;

    @OneToMany(targetEntity = Order.class, mappedBy = "buyer", fetch = FetchType.EAGER)
    Set<Order> orders;
}
