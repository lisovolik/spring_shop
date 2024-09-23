package com.lisovolik.spring_shop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Alexandr Lisovolik on  23.09.2024
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerResponseForList<T> {
    private  Integer total;
    private  Integer limit;
    private  Integer offset;
    private List<T> items;
}
