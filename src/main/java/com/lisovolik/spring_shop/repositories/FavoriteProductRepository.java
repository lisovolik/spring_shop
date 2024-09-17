package com.lisovolik.spring_shop.repositories;

import com.lisovolik.spring_shop.entity.FavoriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alexandr Lisovolik on  11.09.2024
 */

public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Long> {
    List<FavoriteProduct> findAllByUserId(Long userId);
    Optional<FavoriteProduct> findFavoriteProductByUserIdAndProductId(Long userId, Long productId);

    void deleteById(Long id);
}
