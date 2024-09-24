package com.lisovolik.spring_shop.mapper;

import com.lisovolik.spring_shop.entity.Address;
import com.lisovolik.spring_shop.entity.GroupProduct;
import com.lisovolik.spring_shop.models.dto.address.AddressDto;
import com.lisovolik.spring_shop.models.dto.groups.GroupProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * Created by Alexandr Lisovolik on  24.09.2024
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GroupProductMapper {
    GroupProductDto toGroupResponse(GroupProduct group);

    List<GroupProductDto> toGroupResponseList(List<GroupProduct> groups);

}
