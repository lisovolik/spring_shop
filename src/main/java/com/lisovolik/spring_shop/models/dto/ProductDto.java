package com.lisovolik.spring_shop.models.dto;

import com.lisovolik.spring_shop.entity.GroupProduct;
import com.lisovolik.spring_shop.entity.ProductPicture;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandr Lisovolik on  23.09.2024
 */

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private GroupProduct groupProduct;
    List<ProductPicture> pictures = new ArrayList<>();
}
