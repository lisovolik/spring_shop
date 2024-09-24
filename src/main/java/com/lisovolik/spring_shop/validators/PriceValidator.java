package com.lisovolik.spring_shop.validators;

import com.lisovolik.spring_shop.exceptions.ErrorMessages;
import com.lisovolik.spring_shop.exceptions.NotValidException;
import com.lisovolik.spring_shop.models.dto.product_price.AddPriceDto;
import com.lisovolik.spring_shop.models.dto.user.CreateUserRequestDto;
import io.micrometer.common.util.StringUtils;

/**
 * Created by Alexandr Lisovolik on  24.09.2024
 */

public class PriceValidator {
    private PriceValidator(){

    }

    public static void validate(AddPriceDto price) {
        if (price.getPrice() < 0.1){
            throw new NotValidException(ErrorMessages.NAME_CANT_BE_EMPTY.getMessage());
        }
    }
}
