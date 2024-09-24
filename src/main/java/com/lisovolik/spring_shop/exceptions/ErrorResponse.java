package com.lisovolik.spring_shop.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@Getter
public class ErrorResponse {
    private String message;
    private HttpStatus status;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
