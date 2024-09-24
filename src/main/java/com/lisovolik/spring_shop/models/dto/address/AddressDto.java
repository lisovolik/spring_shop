package com.lisovolik.spring_shop.models.dto.address;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@Data
public class AddressDto {
    private Long id;
    private String city;
    private String street;
    private String number;
    private String flat;
}
