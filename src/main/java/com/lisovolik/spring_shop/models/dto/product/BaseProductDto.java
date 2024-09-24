package com.lisovolik.spring_shop.models.dto.product;

import com.lisovolik.spring_shop.models.dto.product_price.AddPriceDto;
import lombok.Data;

/**
 * Created by Alexandr Lisovolik on  24.09.2024
 */

@Data
public class BaseProductDto {

    private String name;
    private String description;
    private Long groupId;
}
