package com.lisovolik.spring_shop.exceptions;

/**
 * Created by Alexandr Lisovolik on  06.09.2024
 */

public enum ErrorMessages {
    NOT_FOUND("Not found"),
    USER_NOT_FOUND("User not found"),
    NAME_CANT_BE_EMPTY("Name can't be empty."),
    DESCRIPTION_CANT_BE_EMPTY("Description can't be empty."),
    EMAIL_CANT_BE_EMPTY("Email can't be empty."),
    USER_WITH_EMAIL_REGISTERED("User with this email already registered."),

    GROUP_PRODUCT_NOT_FOUND("Group product not found."),
    ADDRESS_NOT_FOUND("Address not found."),
    PHONE_NOT_FOUND("Phone not found."),
    PRODUCT_NOT_FOUND("Product not found.");


    private final String message;

    ErrorMessages(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
