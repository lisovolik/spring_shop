package com.lisovolik.spring_shop.models.dto.groups;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Alexandr Lisovolik on  24.09.2024
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class GroupProductDto extends BaseGroupProductDto {
    private Long id;
}
