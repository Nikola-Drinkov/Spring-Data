package com.example.springdataautomapping.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @ManyToOne()
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @ManyToMany
    @JoinColumn(name = "game_id")
    Set<Game> games;
}
