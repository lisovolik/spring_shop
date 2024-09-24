package com.lisovolik.spring_shop.services;

import com.lisovolik.spring_shop.entity.CustomUser;
import com.lisovolik.spring_shop.exceptions.UserNotFoundException;
import com.lisovolik.spring_shop.mapper.UserMapper;
import com.lisovolik.spring_shop.models.dto.user.UserResponseDto;
import com.lisovolik.spring_shop.security.CustomUserRepository;
import com.lisovolik.spring_shop.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by Alexandr Lisovolik on  17.09.2024
 */

@Service
@AllArgsConstructor
public class UserProfileService {
    private final CustomUserRepository userRepository;
    private final UserMapper userMapper;

    public ResponseEntity<UserResponseDto> getProfile(String userName){
        Optional<CustomUser> userOptional = userRepository.findByUsername(userName);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException();
        }
        UserResponseDto user = userMapper.toUserResponse(userOptional.get());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }

    public Long getUserId(String userName){
        Optional<CustomUser> userOptional = userRepository.findByUsername(userName);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException();
        }
        return userOptional.get().getId();
    }

    @Transactional
    public void updateLastLogin(String userName) {
        Long userId = getUserId(userName);
        userRepository.updateLastLogin(Utils.getLocalDateTime(), userId);
    }
}
