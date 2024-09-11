package com.lisovolik.spring_shop.exceptions;

import lombok.Getter;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@Getter
public class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
