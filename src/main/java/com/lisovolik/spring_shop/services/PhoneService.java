package com.lisovolik.spring_shop.services;

import com.lisovolik.spring_shop.entity.Address;
import com.lisovolik.spring_shop.entity.CustomUser;
import com.lisovolik.spring_shop.entity.Phone;
import com.lisovolik.spring_shop.exceptions.UserNotFoundException;
import com.lisovolik.spring_shop.models.AddressDto;
import com.lisovolik.spring_shop.models.PhoneDto;
import com.lisovolik.spring_shop.repositories.AddressRepository;
import com.lisovolik.spring_shop.repositories.PhoneRepository;
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
public class PhoneService {
    private final PhoneRepository phoneRepository;
    private final CustomUserRepository userRepository;

    public ResponseEntity<List<Phone>> getAll(String userName){
        Optional<CustomUser> userOptional = userRepository.findByUsername(userName);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException();
        }
        Long userId = userOptional.get().getId();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(phoneRepository.findPhoneByUserId(userId));
    }

    public ResponseEntity<Phone> save(PhoneDto phoneDto, String userName){
        Optional<CustomUser> userOptional = userRepository.findByUsername(userName);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException();
        }
        Long userId = userOptional.get().getId();
        Phone savePhone = new Phone();

        if (phoneDto.getId() != null){
            Optional<Phone> optional = phoneRepository.findById(phoneDto.getId());
            if (optional.isPresent()){
                savePhone.setId(optional.get().getId());
            }
        }
        savePhone.setNumber(phoneDto.getNumber());
        savePhone.setUserId(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(phoneRepository.save(savePhone));
    }

    public ResponseEntity<String> delete(Long id, String userName){
        Optional<CustomUser> userOptional = userRepository.findByUsername(userName);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException();
        }

        Optional<Phone> optional = phoneRepository.findById(id);
        if (optional.isPresent()){
            phoneRepository.delete(optional.get());
        }

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("Deleted");
    }
}
