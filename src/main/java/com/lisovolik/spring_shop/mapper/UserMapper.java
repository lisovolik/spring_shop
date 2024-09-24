package com.lisovolik.spring_shop.mapper;

import com.lisovolik.spring_shop.entity.CustomUser;
import com.lisovolik.spring_shop.models.dto.user.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

/**
 * Created by Alexandr Lisovolik on  24.09.2024
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(source = "role", target = "role", defaultValue = "USER")
    UserResponseDto toUserResponse(CustomUser user);


}
