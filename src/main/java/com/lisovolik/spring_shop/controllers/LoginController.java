package com.lisovolik.spring_shop.controllers;

import com.lisovolik.spring_shop.entity.user.CustomUser;
import com.lisovolik.spring_shop.security.jwt.JwtUtil;
import com.lisovolik.spring_shop.services.UserLoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@RestController
@AllArgsConstructor
public class LoginController {
    private final UserLoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CustomUser user){
        return loginService.login(user);
    }
}
