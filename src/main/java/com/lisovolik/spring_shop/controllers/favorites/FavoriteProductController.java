package com.lisovolik.spring_shop.controllers.favorites;

import com.lisovolik.spring_shop.admin.GroupProductService;
import com.lisovolik.spring_shop.entity.FavoriteProduct;
import com.lisovolik.spring_shop.entity.product.GroupProduct;
import com.lisovolik.spring_shop.entity.product.Product;
import com.lisovolik.spring_shop.models.CreateGroupProductDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
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
        return service.getAll(authentication.getPrincipal().toString());
    }


}
