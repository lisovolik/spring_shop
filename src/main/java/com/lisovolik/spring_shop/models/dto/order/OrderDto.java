package com.lisovolik.spring_shop.models.dto.order;

import com.lisovolik.spring_shop.entity.OrderItem;
import com.lisovolik.spring_shop.entity.OrderStatus;
import com.lisovolik.spring_shop.entity.OrderStatusConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Alexandr Lisovolik on  24.09.2024
 */

@Data
public class OrderDto {

    private Long id;
    private Long userId;
    private String address;
    private String phone;
    private List<OrderItemDto> products;
    private LocalDateTime createdOn;
    private OrderStatus status = OrderStatus.NEW;
}

