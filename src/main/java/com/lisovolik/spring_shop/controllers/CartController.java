package com.lisovolik.spring_shop.controllers;

import com.lisovolik.spring_shop.entity.CartProduct;
import com.lisovolik.spring_shop.entity.Product;
import com.lisovolik.spring_shop.models.cart.AddProductToCartDto;
import com.lisovolik.spring_shop.models.cart.CartProductDto;
import com.lisovolik.spring_shop.services.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Alexandr Lisovolik on  17.09.2024
 */

@RestController
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService service;

    @GetMapping()
    public ResponseEntity<List<CartProductDto>> getAllProductsInCart(Authentication authentication){
        return service.getProducts(authentication.getPrincipal().toString());
    }

    @PostMapping()
    public ResponseEntity<CartProductDto> addProductsToCart(@RequestBody AddProductToCartDto product, Authentication authentication){
        return service.addProductToCart(product, authentication.getPrincipal().toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductFromCart(@PathVariable Long id, Authentication authentication){
        return service.deleteProductFromCart(id, authentication.getPrincipal().toString());
    }
}
