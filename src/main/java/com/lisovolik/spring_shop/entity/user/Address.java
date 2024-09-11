package com.lisovolik.spring_shop.entity.user;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@Entity
@Table(name = "address")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private String number;

    @Column(name = "flat")
    private String flat;

    @Column(name = "user_id")
    private Long userId;

}
