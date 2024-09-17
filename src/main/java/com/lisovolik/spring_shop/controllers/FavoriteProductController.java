package com.lisovolik.spring_shop.controllers;

import com.lisovolik.spring_shop.entity.Product;
import com.lisovolik.spring_shop.services.FavoriteProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Alexandr Lisovolik on  11.09.2024
 */

@RestController
@AllArgsConstructor
@RequestMapping("/favorites")
public class FavoriteProductController {
    private final FavoriteProductService service;

    @GetMapping()
    public ResponseEntity<List<Product>> getAllFavoriteProducts(Authentication authentication){
        System.out.println(authentication.getPrincipal().toString());
        //return ResponseEntity.ok(Collections.emptyList());
        return service.getAllFavorites(authentication.getPrincipal().toString());
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> setIsProducts(@PathVariable Long id, Authentication authentication){
        System.out.println(authentication.getPrincipal().toString());
        return service.setIsFavorite(id, authentication.getPrincipal().toString());
    }


}
