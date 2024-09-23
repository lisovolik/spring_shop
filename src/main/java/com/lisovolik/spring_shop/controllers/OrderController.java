package com.lisovolik.spring_shop.controllers;

import com.lisovolik.spring_shop.entity.Order;
import com.lisovolik.spring_shop.models.UserDataForOrderDto;
import com.lisovolik.spring_shop.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Alexandr Lisovolik on  19.09.2024
 */

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(
            @RequestBody UserDataForOrderDto userData,
            Authentication authentication){
        return orderService.createOrder(userData, authentication.getPrincipal().toString());
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAllOrders(Authentication authentication){
        return orderService.findAll(authentication.getPrincipal().toString());
    }
}
