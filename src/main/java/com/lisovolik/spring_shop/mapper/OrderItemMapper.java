package com.lisovolik.spring_shop.mapper;

import com.lisovolik.spring_shop.entity.Order;
import com.lisovolik.spring_shop.entity.OrderItem;
import com.lisovolik.spring_shop.models.dto.address.AddressDto;
import com.lisovolik.spring_shop.models.dto.order.OrderItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * Created by Alexandr Lisovolik on  24.09.2024
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {
    OrderItemDto toOrderItemResponse(OrderItem item);

    List<OrderItemDto> toOrderItemResponseList(List<OrderItem> items);

}
