package com.lisovolik.spring_shop.services;

import com.lisovolik.spring_shop.entity.CustomUser;
import com.lisovolik.spring_shop.exceptions.ErrorMessages;
import com.lisovolik.spring_shop.exceptions.NotValidException;
import com.lisovolik.spring_shop.mapper.UserMapper;
import com.lisovolik.spring_shop.models.dto.user.CreateUserRequestDto;
import com.lisovolik.spring_shop.models.dto.user.UserResponseDto;
import com.lisovolik.spring_shop.repositories.UserRegistrationRepository;
import com.lisovolik.spring_shop.utils.Utils;
import com.lisovolik.spring_shop.validators.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@Service
@AllArgsConstructor
public class UserRegistrationService {
    private final UserRegistrationRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public ResponseEntity<UserResponseDto> registerUser(CreateUserRequestDto userDto){
        //verify user
        UserValidator.validate(userDto);

        //check email
        Optional<CustomUser> userOptional = repository.findByEmail(userDto.getEmail());
        if (userOptional.isPresent()){
            throw new NotValidException(ErrorMessages.USER_WITH_EMAIL_REGISTERED.getMessage());
        }

        CustomUser user = new CustomUser();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setRole("USER");
        user.setCreated_on(Utils.getLocalDateTime());
        user.setLastLogin(Utils.getLocalDateTime());
        CustomUser customUser = repository.save(user);

        UserResponseDto response = userMapper.toUserResponse(customUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
