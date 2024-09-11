package com.lisovolik.spring_shop.models;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@Data
public class CreateUserResponseDto {
    private Long id;
    private String username;
    private String email;
    private AddressDto address;
    private PhoneDto phone;
    private String role;
    private LocalDateTime created_on;
    private LocalDateTime last_login;
}
