package com.lisovolik.spring_shop.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JoinFormula;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 1024)
    private String name;

    @Column(name = "description", length = 4000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_group")
    private GroupProduct groupProduct;

    @OneToMany(mappedBy = "idProduct",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    List<ProductPicture> pictures = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinFormula(value = "(SELECT p.id FROM product_price p WHERE p.product_id = id ORDER BY p.created_on DESC LIMIT 1)")
    private ProductPrice price;

    @Column(name = "deleted")
    private boolean deleted;
}
