package com.lisovolik.spring_shop.entity.product;

import com.lisovolik.spring_shop.entity.picture.ProductPicture;
import com.lisovolik.spring_shop.entity.user.Address;
import jakarta.persistence.*;
import lombok.Data;

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
}
