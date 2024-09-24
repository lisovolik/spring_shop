package com.lisovolik.spring_shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Alexandr Lisovolik on  19.09.2024
 */

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> products;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(length = 1, nullable = false)
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus status = OrderStatus.NEW;

    @Column(name = "deleted")
    private boolean deleted;
}

