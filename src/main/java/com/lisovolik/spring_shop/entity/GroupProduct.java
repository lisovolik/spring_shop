package com.lisovolik.spring_shop.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@Entity
@Table(name = "group_product")
@Data
public class GroupProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "deleted")
    private boolean deleted;
}
