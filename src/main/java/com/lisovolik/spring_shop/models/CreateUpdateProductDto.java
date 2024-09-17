package com.lisovolik.spring_shop.models;

import lombok.Data;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@Data
public class CreateUpdateProductDto {
    private String name;
    private String description;
    private Long groupId;
}
