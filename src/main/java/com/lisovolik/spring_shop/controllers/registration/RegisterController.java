package com.lisovolik.spring_shop.controllers.registration;

import com.lisovolik.spring_shop.entity.user.CustomUser;
import com.lisovolik.spring_shop.models.CreateUserRequestDto;
import com.lisovolik.spring_shop.models.CreateUserResponseDto;
import com.lisovolik.spring_shop.services.UserRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@RestController
@AllArgsConstructor
@RequestMapping("/register")
public class RegisterController {
    private final UserRegistrationService userRegistrationService;

    @PostMapping
    public ResponseEntity<CreateUserResponseDto> register(@RequestBody CreateUserRequestDto user){
        return userRegistrationService.registerUser(user);
    }

    @PostMapping("/hello")
    public ResponseEntity<String> register(){
        return ResponseEntity.ok("Hello");
    }
}
