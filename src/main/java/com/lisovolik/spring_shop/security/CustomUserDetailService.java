package com.lisovolik.spring_shop.security;

import com.lisovolik.spring_shop.entity.user.CustomUser;
import com.lisovolik.spring_shop.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final CustomUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CustomUser> optionalUser = repository.findByUsername(username);
        if (optionalUser.isPresent()){
            CustomUser user = optionalUser.get();
            return User.withUsername(
                    user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build();
        } else {
            throw new UserNotFoundException();
        }
    }
}
