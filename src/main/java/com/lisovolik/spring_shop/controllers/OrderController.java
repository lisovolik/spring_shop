package com.lisovolik.spring_shop.controllers;

import com.lisovolik.spring_shop.entity.Order;
import com.lisovolik.spring_shop.models.dto.order.OrderDto;
import com.lisovolik.spring_shop.models.dto.order.UserDataForOrderDto;
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
    public ResponseEntity<OrderDto> createOrder(
            @RequestBody UserDataForOrderDto userData,
            Authentication authentication){
        return orderService.createOrder(userData, authentication.getPrincipal().toString());
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> findAllOrders(Authentication authentication){
        return orderService.findAll(authentication.getPrincipal().toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id, Authentication authentication){
        return orderService.delete(id, authentication.getPrincipal().toString());
    }
}
