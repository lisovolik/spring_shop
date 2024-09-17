package com.lisovolik.spring_shop.utils;

import java.nio.file.Path;
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

    public static String getExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');

        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            return "";
        } else {
            return fileName.substring(dotIndex + 1);
        }
    }
}
