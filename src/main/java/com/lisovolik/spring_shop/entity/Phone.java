package com.lisovolik.spring_shop.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@Entity
@Table(name = "phone")
@Data
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "user_id")
    private Long userId;

}
