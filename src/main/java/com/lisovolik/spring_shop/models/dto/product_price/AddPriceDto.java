package com.lisovolik.spring_shop.models.dto.product_price;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by Alexandr Lisovolik on  24.09.2024
 */

@Data
public class AddPriceDto {
    private Double price;
    private Long productId;
}
