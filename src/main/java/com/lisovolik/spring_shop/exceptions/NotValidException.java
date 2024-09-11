package com.lisovolik.spring_shop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotValidException extends RuntimeException{
    public NotValidException(String message){
        super(message);
    }
}
