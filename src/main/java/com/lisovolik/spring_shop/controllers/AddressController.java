package com.lisovolik.spring_shop.controllers;

import com.lisovolik.spring_shop.models.dto.address.AddressDto;
import com.lisovolik.spring_shop.models.dto.address.CreateAddressDto;
import com.lisovolik.spring_shop.services.AddressService;
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
    public ResponseEntity<List<AddressDto>> getAllAddresses(Authentication authentication){
        return service.getAddresses(authentication.getPrincipal().toString());
    }

    @PostMapping()
    public ResponseEntity<AddressDto> saveAddress(@RequestBody CreateAddressDto address, Authentication authentication){
        return service.saveNewAddress(address, authentication.getPrincipal().toString());
    }

    @PutMapping()
    public ResponseEntity<AddressDto> editAddress(@RequestBody AddressDto address, Authentication authentication){
        return service.editAddress(address, authentication.getPrincipal().toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id, Authentication authentication){
        return service.deleteAddress(id, authentication.getPrincipal().toString());
    }
}
