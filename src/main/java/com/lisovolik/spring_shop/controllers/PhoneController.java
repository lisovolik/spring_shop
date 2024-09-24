package com.lisovolik.spring_shop.controllers;

import com.lisovolik.spring_shop.models.dto.phone.CreatePhoneDto;
import com.lisovolik.spring_shop.models.dto.phone.EditPhoneDto;
import com.lisovolik.spring_shop.models.dto.phone.PhoneResponseDto;
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
    public ResponseEntity<List<PhoneResponseDto>> getAll(Authentication authentication){
        return service.getAll(authentication.getPrincipal().toString());
    }

    @PostMapping()
    public ResponseEntity<PhoneResponseDto> saveNewPhone(@RequestBody CreatePhoneDto phone, Authentication authentication){
        return service.saveNewPhone(phone, authentication.getPrincipal().toString());
    }

    @PutMapping()
    public ResponseEntity<PhoneResponseDto> editPhone(@RequestBody EditPhoneDto phone, Authentication authentication){
        return service.editPhone(phone, authentication.getPrincipal().toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, Authentication authentication){
        return service.delete(id, authentication.getPrincipal().toString());
    }
}
