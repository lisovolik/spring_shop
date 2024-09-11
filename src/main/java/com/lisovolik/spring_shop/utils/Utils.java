package com.lisovolik.spring_shop.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Alexandr Lisovolik on  11.09.2024
 */

public class Utils {

    public static LocalDateTime getLocalDateTime(){
        return new Date().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
