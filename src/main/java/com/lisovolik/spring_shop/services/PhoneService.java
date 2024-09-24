package com.lisovolik.spring_shop.services;

import com.lisovolik.spring_shop.entity.Phone;
import com.lisovolik.spring_shop.exceptions.ErrorMessages;
import com.lisovolik.spring_shop.exceptions.NotFoundException;
import com.lisovolik.spring_shop.mapper.PhoneMapper;
import com.lisovolik.spring_shop.models.dto.phone.CreatePhoneDto;
import com.lisovolik.spring_shop.models.dto.phone.EditPhoneDto;
import com.lisovolik.spring_shop.models.dto.phone.PhoneResponseDto;
import com.lisovolik.spring_shop.repositories.PhoneRepository;
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
    private final PhoneMapper phoneMapper;
    private final UserProfileService userProfileService;

    public ResponseEntity<List<PhoneResponseDto>> getAll(String userName){
        Long userId = userProfileService.getUserId(userName);
        List<PhoneResponseDto> response = phoneMapper.toPhoneResponseList(phoneRepository.findPhoneByUserId(userId));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    public ResponseEntity<PhoneResponseDto> saveNewPhone(CreatePhoneDto phoneDto, String userName){
        Long userId = userProfileService.getUserId(userName);
        Phone savePhone = new Phone();
        savePhone.setNumber(phoneDto.getNumber());
        savePhone.setUserId(userId);
        PhoneResponseDto response = phoneMapper.toPhoneResponse(phoneRepository.save(savePhone));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    public ResponseEntity<PhoneResponseDto> editPhone(EditPhoneDto phoneDto, String userName){
        Long userId = userProfileService.getUserId(userName);


        if (phoneDto.getId() != null){
            throw new NotFoundException(ErrorMessages.PHONE_NOT_FOUND.getMessage());
        }
        Phone savePhone = new Phone();
        savePhone.setId(phoneDto.getId());
        savePhone.setNumber(phoneDto.getNumber());
        savePhone.setUserId(userId);

        PhoneResponseDto response = phoneMapper.toPhoneResponse(phoneRepository.save(savePhone));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    public ResponseEntity<String> delete(Long id, String userName){
        Long userId = userProfileService.getUserId(userName);

        Optional<Phone> optional = phoneRepository.findById(id);
        if (optional.isPresent()){
            phoneRepository.delete(optional.get());
        }

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("Deleted");
    }

    public String findPhoneById(Long id){
        Optional<Phone> optional = phoneRepository.findById(id);
        if (optional.isPresent()){
            return optional.get().getNumber();
        } else {
            throw new NotFoundException(ErrorMessages.PHONE_NOT_FOUND.getMessage());
        }
    }
}
