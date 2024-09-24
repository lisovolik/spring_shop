package com.lisovolik.spring_shop.mapper;

import com.lisovolik.spring_shop.entity.Address;
import com.lisovolik.spring_shop.entity.CartProduct;
import com.lisovolik.spring_shop.models.cart.CartProductDto;
import com.lisovolik.spring_shop.models.dto.address.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * Created by Alexandr Lisovolik on  24.09.2024
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartMapper {
    CartProductDto toCartResponse(CartProduct product);

    List<CartProductDto> toCartResponseList(List<CartProduct> products);


}
