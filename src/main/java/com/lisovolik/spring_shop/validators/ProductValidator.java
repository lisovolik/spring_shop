package com.lisovolik.spring_shop.validators;

import com.lisovolik.spring_shop.exceptions.ErrorMessages;
import com.lisovolik.spring_shop.exceptions.NotValidException;
import com.lisovolik.spring_shop.models.dto.product.BaseProductDto;
import io.micrometer.common.util.StringUtils;

/**
 * Created by Alexandr Lisovolik on  13.09.2024
 */

public class ProductValidator {
    private ProductValidator(){

    }

    public static void validate(BaseProductDto product) {
        if (StringUtils.isEmpty(product.getName())){
            throw new NotValidException(ErrorMessages.NAME_CANT_BE_EMPTY.getMessage());
        }
        if (StringUtils.isEmpty(product.getDescription())){
            throw new NotValidException(ErrorMessages.DESCRIPTION_CANT_BE_EMPTY.getMessage());
        }
        if (product.getGroupId() == null){
            throw new NotValidException(ErrorMessages.GROUP_PRODUCT_NOT_FOUND.getMessage());
        }
    }
}
