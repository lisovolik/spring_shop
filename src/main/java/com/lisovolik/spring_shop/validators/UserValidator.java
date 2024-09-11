package com.lisovolik.spring_shop.validators;

import com.lisovolik.spring_shop.exceptions.ErrorMessages;
import com.lisovolik.spring_shop.exceptions.NotValidException;
import com.lisovolik.spring_shop.models.CreateUserRequestDto;
import io.micrometer.common.util.StringUtils;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

public class UserValidator {
    private UserValidator(){

    }

    public static void validate(CreateUserRequestDto user) {
        if (StringUtils.isEmpty(user.getUsername())){
            throw new NotValidException(ErrorMessages.NAME_CANT_BE_EMPTY.getMessage());
        }
        if (StringUtils.isEmpty(user.getPassword())){
            throw new NotValidException(ErrorMessages.DESCRIPTION_CANT_BE_EMPTY.getMessage());
        }
        if (StringUtils.isEmpty(user.getEmail())){
            throw new NotValidException(ErrorMessages.EMAIL_CANT_BE_EMPTY.getMessage());
        }
    }
}
