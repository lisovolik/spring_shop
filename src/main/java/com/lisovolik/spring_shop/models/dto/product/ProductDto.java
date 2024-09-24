package com.lisovolik.spring_shop.models.dto.product;

import com.lisovolik.spring_shop.entity.GroupProduct;
import com.lisovolik.spring_shop.entity.ProductPicture;
import com.lisovolik.spring_shop.models.dto.groups.GroupProductDto;
import com.lisovolik.spring_shop.models.dto.product_price.PriceDto;
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
    private GroupProductDto groupProduct;
    private PriceDto price;
    List<ProductPicture> pictures = new ArrayList<>();
}
