package com.lisovolik.spring_shop.models;

import lombok.Data;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@Data
public class AddProductToCartDto {
    private Long productId;
    private Integer quantity;
}
