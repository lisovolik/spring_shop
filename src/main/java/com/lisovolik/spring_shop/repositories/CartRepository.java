package com.lisovolik.spring_shop.repositories;

import com.lisovolik.spring_shop.entity.CartProduct;
import com.lisovolik.spring_shop.entity.FavoriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alexandr Lisovolik on  17.09.2024
 */
@Repository
public interface CartRepository extends JpaRepository<CartProduct, Long> {
    List<CartProduct> findAllByUserId(Long userId);

    Optional<CartProduct> findCartProductByUserIdAndProductId(Long userId, Long productId);

}
