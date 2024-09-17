package com.lisovolik.spring_shop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by Alexandr Lisovolik on  11.09.2024
 */

@Entity
@Data
@Table(name = "favorite_product")
public class FavoriteProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "created_on")
    private LocalDateTime created_on;
}
