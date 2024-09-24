package com.lisovolik.spring_shop.models.dto.user;

import lombok.Data;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@Data
public class CreateUserRequestDto {
    private String username;
    private String password;
    private String email;
}
