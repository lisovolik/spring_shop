package com.lisovolik.spring_shop.services;

import com.lisovolik.spring_shop.entity.*;
import com.lisovolik.spring_shop.exceptions.ErrorMessages;
import com.lisovolik.spring_shop.exceptions.NotFoundException;
import com.lisovolik.spring_shop.mapper.OrderMapper;
import com.lisovolik.spring_shop.models.dto.order.OrderDto;
import com.lisovolik.spring_shop.models.dto.order.UserDataForOrderDto;
import com.lisovolik.spring_shop.repositories.CartRepository;
import com.lisovolik.spring_shop.repositories.OrderRepository;
import com.lisovolik.spring_shop.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    private final OrderMapper orderMapper;

    public ResponseEntity<OrderDto> createOrder(UserDataForOrderDto userData, String userName){
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

        OrderDto response = orderMapper.toOrderResponse(orderRepository.save(order));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);

    }

    public ResponseEntity<List<OrderDto>> findAll(String userName){
        Long userId = userService.getUserId(userName);
        List<OrderDto> response = orderMapper
                .toOrderResponseList(
                        orderRepository.findAllByUserIdAndDeletedFalseOrderByCreatedOnDesc(userId)
                );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    public ResponseEntity<String> delete(Long orderId, String userName){
        Long userId = userService.getUserId(userName);
        Optional<Order> optional = orderRepository.findById(orderId);
        if (optional.isPresent()){
            Order order = optional.get();
            order.setDeleted(true);
            orderRepository.save(order);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body("Deleted");
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorMessages.NOT_FOUND.getMessage());
    }
}
