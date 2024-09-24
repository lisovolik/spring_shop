package com.lisovolik.spring_shop.models.cart;

import com.lisovolik.spring_shop.entity.Product;
import com.lisovolik.spring_shop.models.dto.product.ProductDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by Alexandr Lisovolik on  24.09.2024
 */

public class CartProductDto {
    private Long id;
    private Long userId;
    private ProductDto product;
    private Integer quantity;
    private LocalDateTime created_on;
}
