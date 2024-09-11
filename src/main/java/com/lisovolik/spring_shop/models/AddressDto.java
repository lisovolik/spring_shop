package com.lisovolik.spring_shop.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

public class AddressDto {
    private String city;
    private String street;
    private String number;
    private String flat;
}
