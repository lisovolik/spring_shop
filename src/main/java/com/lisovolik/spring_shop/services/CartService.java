package com.lisovolik.spring_shop.services;

import com.lisovolik.spring_shop.entity.CartProduct;
import com.lisovolik.spring_shop.entity.Product;
import com.lisovolik.spring_shop.entity.CustomUser;
import com.lisovolik.spring_shop.exceptions.ErrorMessages;
import com.lisovolik.spring_shop.exceptions.NotFoundException;
import com.lisovolik.spring_shop.exceptions.UserNotFoundException;
import com.lisovolik.spring_shop.models.AddProductToCartDto;
import com.lisovolik.spring_shop.repositories.CartRepository;
import com.lisovolik.spring_shop.repositories.ProductRepository;
import com.lisovolik.spring_shop.security.CustomUserRepository;
import com.lisovolik.spring_shop.utils.Utils;
import lombok.AllArgsConstructor;
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
    private final CustomUserRepository userRepository;
    private final ProductRepository productRepository;

    public ResponseEntity<List<Product>> getProducts(String userName){
        Optional<CustomUser> userOptional = userRepository.findByUsername(userName);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException();
        }
        Long userId = userOptional.get().getId();
        List<Product> list = cartRepository
                .findAllByUserId(userId)
                .stream().map(CartProduct::getProduct)
                .toList();
        return  ResponseEntity.ok(list);
    }


    public ResponseEntity<CartProduct> addProductToCart(AddProductToCartDto product, String userName){
        Optional<CustomUser> userOptional = userRepository.findByUsername(userName);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException();
        }
        Long userId = userOptional.get().getId();

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
        cartRepository.save(productToSave);
        return  ResponseEntity.ok().body(productToSave);
    }

    public ResponseEntity<String> deleteProductFromCart(Long productId, String userName){
        Optional<CustomUser> userOptional = userRepository.findByUsername(userName);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException();
        }
        Long userId = userOptional.get().getId();

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
