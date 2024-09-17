package com.lisovolik.spring_shop.services;

import com.lisovolik.spring_shop.entity.Address;
import com.lisovolik.spring_shop.entity.CustomUser;
import com.lisovolik.spring_shop.exceptions.UserNotFoundException;
import com.lisovolik.spring_shop.models.AddressDto;
import com.lisovolik.spring_shop.repositories.AddressRepository;
import com.lisovolik.spring_shop.security.CustomUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alexandr Lisovolik on  17.09.2024
 */

@Service
@AllArgsConstructor
public class UserProfileService {
    private final CustomUserRepository userRepository;

    public ResponseEntity<CustomUser> getProfile(String userName){
        Optional<CustomUser> userOptional = userRepository.findByUsername(userName);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userOptional.get());
    }
}
