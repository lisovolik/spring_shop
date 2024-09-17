package com.lisovolik.spring_shop.controllers;

import com.lisovolik.spring_shop.entity.Phone;
import com.lisovolik.spring_shop.models.PhoneDto;
import com.lisovolik.spring_shop.services.PhoneService;
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
@RequestMapping("/phone")
public class PhoneController {
    private final PhoneService service;

    @GetMapping()
    public ResponseEntity<List<Phone>> getAll(Authentication authentication){
        return service.getAll(authentication.getPrincipal().toString());
    }

    @PostMapping()
    public ResponseEntity<Phone> saveAddress(@RequestBody PhoneDto phone, Authentication authentication){
        return service.save(phone, authentication.getPrincipal().toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, Authentication authentication){
        return service.delete(id, authentication.getPrincipal().toString());
    }
}
