package com.lisovolik.spring_shop.mapper;

import com.lisovolik.spring_shop.entity.Address;
import com.lisovolik.spring_shop.entity.Order;
import com.lisovolik.spring_shop.models.dto.address.AddressDto;
import com.lisovolik.spring_shop.models.dto.order.OrderDto;
import org.aspectj.weaver.ast.Or;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * Created by Alexandr Lisovolik on  24.09.2024
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    OrderDto toOrderResponse(Order order);

    List<OrderDto> toOrderResponseList(List<Order> orders);

}
