package com.lisovolik.spring_shop.services;

import com.lisovolik.spring_shop.entity.CartProduct;
import com.lisovolik.spring_shop.entity.Product;
import com.lisovolik.spring_shop.exceptions.ErrorMessages;
import com.lisovolik.spring_shop.exceptions.NotFoundException;
import com.lisovolik.spring_shop.mapper.CartMapper;
import com.lisovolik.spring_shop.models.cart.AddProductToCartDto;
import com.lisovolik.spring_shop.models.cart.CartProductDto;
import com.lisovolik.spring_shop.repositories.CartRepository;
import com.lisovolik.spring_shop.repositories.ProductRepository;
import com.lisovolik.spring_shop.security.CustomUserRepository;
import com.lisovolik.spring_shop.utils.Utils;
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
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;
    private final UserProfileService userProfileService;

    public ResponseEntity<List<CartProductDto>> getProducts(String userName){
        Long userId = userProfileService.getUserId(userName);
        List<CartProduct> list = cartRepository.findAllByUserId(userId);
        List<CartProductDto> response = cartMapper.toCartResponseList(list);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }


    public ResponseEntity<CartProductDto> addProductToCart(AddProductToCartDto product, String userName){
        Long userId = userProfileService.getUserId(userName);

        Optional<Product> productOptional = productRepository.findById(product.getProductId());
        if (productOptional.isEmpty()){
            throw new NotFoundException(ErrorMessages.PRODUCT_NOT_FOUND.getMessage());
        }

        Optional<CartProduct> optionalCartProduct = cartRepository
                .findCartProductByUserIdAndProductId(userId, product.getProductId());

        CartProduct productToSave = new CartProduct();
        productToSave.setUserId(userId);
        productToSave.setProduct(productOptional.get());
        productToSave.setQuantity(product.getQuantity());
        productToSave.setCreated_on(Utils.getLocalDateTime());
        if (optionalCartProduct.isPresent()){
            productToSave.setId(optionalCartProduct.get().getId());
        }
        CartProductDto response = cartMapper.toCartResponse(cartRepository.save(productToSave));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    public ResponseEntity<String> deleteProductFromCart(Long productId, String userName){
        Long userId = userProfileService.getUserId(userName);

        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()){
            throw new NotFoundException(ErrorMessages.PRODUCT_NOT_FOUND.getMessage());
        }

        Optional<CartProduct> optionalCartProduct = cartRepository.findCartProductByUserIdAndProductId(userId, productId);

        if (optionalCartProduct.isPresent()){
            cartRepository.deleteById(optionalCartProduct.get().getId());
        }
        return  ResponseEntity.noContent().build();
    }
}
