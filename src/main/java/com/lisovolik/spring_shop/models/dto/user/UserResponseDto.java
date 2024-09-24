package com.lisovolik.spring_shop.models.dto.user;

import com.lisovolik.spring_shop.models.dto.address.AddressDto;
import com.lisovolik.spring_shop.models.dto.phone.CreatePhoneDto;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private AddressDto address;
    private CreatePhoneDto phone;
    private String role;
    private LocalDateTime created_on;
    private LocalDateTime last_login;
}
