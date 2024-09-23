package com.lisovolik.spring_shop.services;

import com.lisovolik.spring_shop.entity.*;
import com.lisovolik.spring_shop.exceptions.ErrorMessages;
import com.lisovolik.spring_shop.exceptions.NotFoundException;
import com.lisovolik.spring_shop.models.UserDataForOrderDto;
import com.lisovolik.spring_shop.repositories.AddressRepository;
import com.lisovolik.spring_shop.repositories.CartRepository;
import com.lisovolik.spring_shop.repositories.OrderRepository;
import com.lisovolik.spring_shop.repositories.PhoneRepository;
import com.lisovolik.spring_shop.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Alexandr Lisovolik on  19.09.2024
 */

@Service
@AllArgsConstructor
public class OrderService {
    private final UserProfileService userService;
    private final CartRepository cartRepository;
    private final AddressService addressService;
    private final PhoneService phoneService;
    private final OrderRepository orderRepository;

    public ResponseEntity<String> createOrder(UserDataForOrderDto userData, String userName){
        Long userId = userService.getUserId(userName);

        String address = addressService.findAddressById(userData.getAddressId());
        String phone = phoneService.findPhoneById(userData.getPhoneId());

        List<OrderItem> products = cartRepository.findAllByUserId(userId)
                .stream()
                .map(one -> new OrderItem(
                        one.getProduct(),
                        one.getQuantity(),
                        88.99//one.getPrice()
                ))
                .toList();
        if (products.isEmpty()){
            throw new NotFoundException(ErrorMessages.PRODUCT_NOT_FOUND.getMessage());
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setAddress(address);
        order.setPhone(phone);
        order.setProducts(products);
        order.setCreatedOn(Utils.getLocalDateTime());

        orderRepository.save(order);

        return ResponseEntity.status(HttpStatus.OK).body("Saved");

    }

    public ResponseEntity<List<Order>> findAll(String userName){
        Long userId = userService.getUserId(userName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderRepository.findAllByUserIdOrderByCreatedOnDesc(userId));
    }
}
