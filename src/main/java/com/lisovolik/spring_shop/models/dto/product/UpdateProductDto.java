package com.lisovolik.spring_shop.models.dto.product;

import com.lisovolik.spring_shop.entity.GroupProduct;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Alexandr Lisovolik on  24.09.2024
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateProductDto extends BaseProductDto{
    private Long id;
}
