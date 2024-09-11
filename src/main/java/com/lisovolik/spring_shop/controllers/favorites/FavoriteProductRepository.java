package com.lisovolik.spring_shop.controllers.favorites;

import com.lisovolik.spring_shop.entity.FavoriteProduct;
import com.lisovolik.spring_shop.entity.product.GroupProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Alexandr Lisovolik on  11.09.2024
 */

public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Long> {
    List<FavoriteProduct> findAllByUserId(Long user_id);
}
