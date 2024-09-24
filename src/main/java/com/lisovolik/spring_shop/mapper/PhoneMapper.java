package com.lisovolik.spring_shop.mapper;

import com.lisovolik.spring_shop.entity.Phone;
import com.lisovolik.spring_shop.models.dto.phone.PhoneResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * Created by Alexandr Lisovolik on  24.09.2024
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PhoneMapper {
    PhoneResponseDto toPhoneResponse(Phone phone);

    List<PhoneResponseDto> toPhoneResponseList(List<Phone> phones);


}
