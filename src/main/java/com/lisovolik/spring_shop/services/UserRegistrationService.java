package com.lisovolik.spring_shop.services;

import com.lisovolik.spring_shop.entity.user.CustomUser;
import com.lisovolik.spring_shop.exceptions.ErrorMessages;
import com.lisovolik.spring_shop.exceptions.NotValidException;
import com.lisovolik.spring_shop.models.CreateUserRequestDto;
import com.lisovolik.spring_shop.models.CreateUserResponseDto;
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

    public ResponseEntity<CreateUserResponseDto> registerUser(CreateUserRequestDto userDto){
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

        CreateUserResponseDto response = new CreateUserResponseDto();
        response.setId(customUser.getId());
        response.setUsername(customUser.getUsername());
        response.setEmail(customUser.getEmail());
        response.setRole("USER");
        response.setCreated_on(customUser.getCreated_on());
        response.setLast_login(customUser.getLastLogin());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
