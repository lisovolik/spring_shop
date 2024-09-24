package com.lisovolik.spring_shop.models.dto.user;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@Data
public class UserLoginRequestDto {
    private String username;
    private String password;
}
