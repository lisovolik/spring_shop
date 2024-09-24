package com.lisovolik.spring_shop.services;

import com.lisovolik.spring_shop.entity.Address;
import com.lisovolik.spring_shop.exceptions.ErrorMessages;
import com.lisovolik.spring_shop.exceptions.NotFoundException;
import com.lisovolik.spring_shop.mapper.AddressMapper;
import com.lisovolik.spring_shop.models.dto.address.AddressDto;
import com.lisovolik.spring_shop.models.dto.address.CreateAddressDto;
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
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final UserProfileService userProfileService;

    public ResponseEntity<List<AddressDto>> getAddresses(String userName){
        Long userId = userProfileService.getUserId(userName);
        List<AddressDto> response = addressMapper.toAddressResponseList(addressRepository.findAddressByUserId(userId));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    public ResponseEntity<AddressDto> saveNewAddress(CreateAddressDto addressDto, String userName){
        Long userId = userProfileService.getUserId(userName);
        Address saveAddress = new Address();
        saveAddress.setCity(addressDto.getCity());
        saveAddress.setStreet(addressDto.getStreet());
        saveAddress.setFlat(addressDto.getFlat());
        saveAddress.setNumber(addressDto.getNumber());
        saveAddress.setUserId(userId);
        AddressDto response = addressMapper.toAddressResponse(addressRepository.save(saveAddress));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    public ResponseEntity<AddressDto> editAddress(AddressDto addressDto, String userName){
        Long userId = userProfileService.getUserId(userName);

        if (addressDto.getId() != null){
            throw new NotFoundException(ErrorMessages.ADDRESS_NOT_FOUND.getMessage());
        }

        Address saveAddress = new Address();
        saveAddress.setId(addressDto.getId());
        saveAddress.setCity(addressDto.getCity());
        saveAddress.setStreet(addressDto.getStreet());
        saveAddress.setFlat(addressDto.getFlat());
        saveAddress.setNumber(addressDto.getNumber());
        saveAddress.setUserId(userId);
        AddressDto response = addressMapper.toAddressResponse(addressRepository.save(saveAddress));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    public ResponseEntity<String> deleteAddress(Long id, String userName){
        userProfileService.getUserId(userName);

        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()){
            addressRepository.delete(optionalAddress.get());
        }

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("Deleted");
    }

    public String findAddressById(Long addressId){
        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        if (optionalAddress.isPresent()){
            Address address = optionalAddress.get();
            return address.getCity() + " " +
                    address.getStreet() + " " +
                    address.getNumber() + " " +
                    address.getFlat();
        } else {
            throw new NotFoundException(ErrorMessages.ADDRESS_NOT_FOUND.getMessage());
        }
    }
}
