package com.lisovolik.spring_shop.services;

import com.lisovolik.spring_shop.entity.user.CustomUser;
import com.lisovolik.spring_shop.security.jwt.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@Service
@AllArgsConstructor
public class UserLoginService {
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<String> login(CustomUser user){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = JwtUtil.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(jwtToken);
    }
}
