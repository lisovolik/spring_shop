package com.lisovolik.spring_shop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by Alexandr Lisovolik on  24.09.2024
 */

@Entity
@Table(name = "product_price")
@Data
public class ProductPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private Double price;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "product_id")
    private Long productId;

}
