package com.lisovolik.spring_shop.services;

import com.lisovolik.spring_shop.entity.FavoriteProduct;
import com.lisovolik.spring_shop.entity.Product;
import com.lisovolik.spring_shop.entity.CustomUser;
import com.lisovolik.spring_shop.exceptions.ErrorMessages;
import com.lisovolik.spring_shop.exceptions.NotFoundException;
import com.lisovolik.spring_shop.exceptions.UserNotFoundException;
import com.lisovolik.spring_shop.repositories.FavoriteProductRepository;
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
 * Created by Alexandr Lisovolik on  11.09.2024
 */

@Service
@AllArgsConstructor
public class FavoriteProductService {
    private final FavoriteProductRepository favoriteRepository;
    private final CustomUserRepository userRepository;
    private final ProductRepository productRepository;

    public ResponseEntity<List<Product>> getAllFavorites(String userName){
        Optional<CustomUser> userOptional = userRepository.findByUsername(userName);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException();
        }
        Long userId = userOptional.get().getId();
        List<Product> list = favoriteRepository
                .findAllByUserId(userId)
                .stream().map(FavoriteProduct::getProduct)
                .toList();
        return  ResponseEntity.ok(list);
    }

    public ResponseEntity<String> setIsFavorite(Long productId, String userName){
        Long userId = getUserId(userName);
        Long favoriteId = checkInFavorites(productId, userId);

        if (favoriteId > 0) {
            favoriteRepository.deleteById(favoriteId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product was deleted from favorites.");
        } else {
            saveProductToFavorites(userId, productId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product was added to favorites.");
        }
    }

    private void saveProductToFavorites(Long userId, Long productId) {
        Product product = getProductById(productId);

        FavoriteProduct favorite = new FavoriteProduct();
        favorite.setUserId(userId);
        favorite.setCreated_on(Utils.getLocalDateTime());
        favorite.setProduct(product);
        favoriteRepository.save(favorite);
    }

    private Product getProductById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()){
            throw new NotFoundException(ErrorMessages.PRODUCT_NOT_FOUND.getMessage());
        }
        return productOptional.get();
    }

    private Long checkInFavorites(Long productId, Long userId) {
        Optional<FavoriteProduct> favoriteOptional = favoriteRepository.findFavoriteProductByUserIdAndProductId(userId, productId);
        if(favoriteOptional.isPresent()){
            return favoriteOptional.get().getId();
        } else {
            return -1L;
        }
    }

    private Long getUserId(String userName) {
        Optional<CustomUser> userOptional = userRepository.findByUsername(userName);
        if (userOptional.isEmpty()){
            throw new UserNotFoundException();
        }
        return userOptional.get().getId();
    }

}
