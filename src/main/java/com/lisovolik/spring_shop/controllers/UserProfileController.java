package com.lisovolik.spring_shop.controllers;

import com.lisovolik.spring_shop.entity.Address;
import com.lisovolik.spring_shop.entity.CustomUser;
import com.lisovolik.spring_shop.models.AddressDto;
import com.lisovolik.spring_shop.services.AddressService;
import com.lisovolik.spring_shop.services.UserProfileService;
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
@RequestMapping("/profile")
public class UserProfileController {
    private final UserProfileService service;

    @GetMapping()
    public ResponseEntity<CustomUser> getProfile(Authentication authentication){
        return service.getProfile(authentication.getPrincipal().toString());
    }
}
