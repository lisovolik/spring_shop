package com.lisovolik.spring_shop.services;

import com.lisovolik.spring_shop.entity.Address;
import com.lisovolik.spring_shop.entity.CartProduct;
import com.lisovolik.spring_shop.entity.CustomUser;
import com.lisovolik.spring_shop.entity.Product;
import com.lisovolik.spring_shop.exceptions.UserNotFoundException;
import com.lisovolik.spring_shop.models.AddressDto;
import com.lisovolik.spring_shop.repositories.AddressRepository;
import com.lisovolik.spring_shop.security.CustomUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
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
    private final CustomUserRepository userRepository;

    public ResponseEntity<List<Address>> getAddresses(String userName){
        Optional<CustomUser> userOptional = userRepository.findByUsername(userName);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException();
        }
        Long userId = userOptional.get().getId();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(addressRepository.findAddressByUserId(userId));
    }

    public ResponseEntity<Address> saveAddress(AddressDto addressDto, String userName){
        Optional<CustomUser> userOptional = userRepository.findByUsername(userName);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException();
        }
        Long userId = userOptional.get().getId();
        Address saveAddress = new Address();

        if (addressDto.getId() != null){
            Optional<Address> optionalAddress = addressRepository.findById(addressDto.getId());
            if (optionalAddress.isPresent()){
                saveAddress.setId(optionalAddress.get().getId());
            }
        }
        saveAddress.setCity(addressDto.getCity());
        saveAddress.setStreet(addressDto.getStreet());
        saveAddress.setFlat(addressDto.getFlat());
        saveAddress.setNumber(addressDto.getNumber());
        saveAddress.setUserId(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(addressRepository.save(saveAddress));
    }

    public ResponseEntity<String> deleteAddress(Long id, String userName){
        Optional<CustomUser> userOptional = userRepository.findByUsername(userName);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException();
        }

        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()){
            addressRepository.delete(optionalAddress.get());
        }

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("Deleted");
    }
}
