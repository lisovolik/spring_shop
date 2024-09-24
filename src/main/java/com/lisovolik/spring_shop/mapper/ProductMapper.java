package com.lisovolik.spring_shop.mapper;

import com.lisovolik.spring_shop.entity.Address;
import com.lisovolik.spring_shop.entity.Product;
import com.lisovolik.spring_shop.models.dto.address.AddressDto;
import com.lisovolik.spring_shop.models.dto.product.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * Created by Alexandr Lisovolik on  24.09.2024
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    ProductDto toProductResponse(Product product);

    List<ProductDto> toProductResponseList(List<Product> product);


}
