package com.lisovolik.spring_shop.models.dto.order;

import com.lisovolik.spring_shop.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@Data
public class OrderItemDto {
    private Long id;
    private Product product;
    private Integer quantity;
    private Double price;
}
