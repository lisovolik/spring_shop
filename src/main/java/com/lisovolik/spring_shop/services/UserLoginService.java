package com.lisovolik.spring_shop.services;

import com.lisovolik.spring_shop.models.dto.user.UserLoginRequestDto;
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
    private final UserProfileService userProfileService;

    public ResponseEntity<String> login(UserLoginRequestDto customUser){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                customUser.getUsername(),
                customUser.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        String jwtToken = new JwtUtil().generateToken(user);
        userProfileService.updateLastLogin(user.getUsername());

        return ResponseEntity.ok(jwtToken);
    }
}
