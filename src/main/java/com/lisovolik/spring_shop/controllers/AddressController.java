package com.lisovolik.spring_shop.controllers;

import com.lisovolik.spring_shop.entity.Address;
import com.lisovolik.spring_shop.entity.CartProduct;
import com.lisovolik.spring_shop.entity.Product;
import com.lisovolik.spring_shop.models.AddProductToCartDto;
import com.lisovolik.spring_shop.models.AddressDto;
import com.lisovolik.spring_shop.services.AddressService;
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
@RequestMapping("/address")
public class AddressController {
    private final AddressService service;

    @GetMapping()
    public ResponseEntity<List<Address>> getAllAddresses(Authentication authentication){
        return service.getAddresses(authentication.getPrincipal().toString());
    }

    @PostMapping()
    public ResponseEntity<Address> saveAddress(@RequestBody AddressDto address, Authentication authentication){
        return service.saveAddress(address, authentication.getPrincipal().toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id, Authentication authentication){
        return service.deleteAddress(id, authentication.getPrincipal().toString());
    }
}
