package com.lisovolik.spring_shop.controllers.favorites;

import com.lisovolik.spring_shop.entity.FavoriteProduct;
import com.lisovolik.spring_shop.entity.product.Product;
import com.lisovolik.spring_shop.entity.user.CustomUser;
import com.lisovolik.spring_shop.exceptions.UserNotFoundException;
import com.lisovolik.spring_shop.security.CustomUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
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

    public ResponseEntity<List<Product>> getAll(String userName){
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

    /*public ResponseEntity<String> setIsFavorite(Long id, boolean isFavorite, UserDetails userDetails){
        Optional<FavoriteProduct> optional = repository.findById(id);
        if (optional.isEmpty()){
            FavoriteProduct product = new FavoriteProduct();
            product.setId(id);
            product
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
        }
    }*/

}
