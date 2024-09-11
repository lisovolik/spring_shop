package com.lisovolik.spring_shop.validators;

import com.lisovolik.spring_shop.entity.product.GroupProduct;
import com.lisovolik.spring_shop.exceptions.ErrorMessages;
import com.lisovolik.spring_shop.exceptions.NotValidException;
import com.lisovolik.spring_shop.models.CreateGroupProductDto;
import io.micrometer.common.util.StringUtils;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

public class GroupProductValidator {
    private GroupProductValidator(){

    }

    public static void validate(CreateGroupProductDto group) {
        validate(group.getName(), group.getDescription());
    }

    public static void validate(GroupProduct group) {
        validate(group.getName(), group.getDescription());
    }

    public static void validate(String name, String description){
        if (StringUtils.isEmpty(name)){
            throw new NotValidException(ErrorMessages.NAME_CANT_BE_EMPTY.getMessage());
        }
        if (StringUtils.isEmpty(description)){
            throw new NotValidException(ErrorMessages.DESCRIPTION_CANT_BE_EMPTY.getMessage());
        }
    }
}
