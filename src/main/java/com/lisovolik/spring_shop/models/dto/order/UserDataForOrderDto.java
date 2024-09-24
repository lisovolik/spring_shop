package com.lisovolik.spring_shop.models.dto.order;

import lombok.Data;

/**
 * Created by Alexandr Lisovolik on  19.09.2024
 */

@Data
public class UserDataForOrderDto {
    private Long addressId;
    private Long phoneId;
}
