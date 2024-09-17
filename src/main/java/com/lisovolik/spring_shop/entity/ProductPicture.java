package com.lisovolik.spring_shop.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@Entity
@Table(name = "pictures_product")
@Data
public class ProductPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 1024)
    private String name;

    @Column(name = "url", length = 2048)
    private String url;

    @Column(name = "order_id")
    private Integer order;

    @Column(name="id_product")
    private Long idProduct;
}
