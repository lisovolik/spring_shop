package com.lisovolik.spring_shop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Alexandr Lisovolik on  19.09.2024
 */

@Getter
@AllArgsConstructor
public enum OrderStatus {
    NEW("N"),
    PROCESSING("P"),
    DONE("D"),
    CANCELED("C");

    private final String code;

}
