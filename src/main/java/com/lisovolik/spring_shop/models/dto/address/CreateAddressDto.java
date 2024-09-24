package com.lisovolik.spring_shop.models.dto.address;

import lombok.Data;

/**
 * Created by Alexandr Lisovolik on  24.09.2024
 */

@Data
public class CreateAddressDto {
    private String city;
    private String street;
    private String number;
    private String flat;
}
