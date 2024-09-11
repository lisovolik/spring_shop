package com.lisovolik.spring_shop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GroupProductNotFoundException extends RuntimeException{
    public GroupProductNotFoundException(String message){
        super(message);
    }
}
