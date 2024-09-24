package com.lisovolik.spring_shop.mapper;

import com.lisovolik.spring_shop.entity.Address;
import com.lisovolik.spring_shop.entity.ProductPrice;
import com.lisovolik.spring_shop.models.dto.address.AddressDto;
import com.lisovolik.spring_shop.models.dto.product_price.PriceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * Created by Alexandr Lisovolik on  24.09.2024
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductPriceMapper {

    //@Mapping(source = "numberOfSeats", target = "seatCount")
    PriceDto toPriceResponse(ProductPrice price);

    List<PriceDto> toPriceResponseList(List<ProductPrice> prices);


}
