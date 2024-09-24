package com.lisovolik.spring_shop.controllers;

import com.lisovolik.spring_shop.models.dto.product.FavoriteProductRequest;
import com.lisovolik.spring_shop.models.ServerResponseForList;
import com.lisovolik.spring_shop.models.dto.product.ProductDto;
import com.lisovolik.spring_shop.services.FavoriteProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Alexandr Lisovolik on  11.09.2024
 */

@RestController
@AllArgsConstructor
@RequestMapping("/favorites")
public class FavoriteProductController {
    private final FavoriteProductService service;

    @GetMapping()
    public ResponseEntity<ServerResponseForList<ProductDto>> getAllFavoriteProducts(
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            Authentication authentication){
        return service.getAllFavorites(limit, offset, authentication.getPrincipal().toString());
    }

    @PostMapping
    public ResponseEntity<String> setIsProducts(@RequestBody FavoriteProductRequest product, Authentication authentication){
        return service.setIsFavorite(product.getProductId(), authentication.getPrincipal().toString());
    }


}
