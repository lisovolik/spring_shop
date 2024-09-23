package com.lisovolik.spring_shop.repositories;

import com.lisovolik.spring_shop.entity.FavoriteProduct;
import com.lisovolik.spring_shop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alexandr Lisovolik on  11.09.2024
 */

public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Long> {
    @Query(value = "select p from Product p " +
            "inner join FavoriteProduct f on (f.product.id = p.id and f.userId=:userId) ")
    List<Product> findAllFavoriteProductsByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query(value = "select count(*) from product p " +
            "inner join favorite_product f on (f.product_id = p.id and f.user_id=:userId) " , nativeQuery = true)
    Integer getCountForFavoriteList(@Param("userId") Long userId);

    Optional<FavoriteProduct> findFavoriteProductByUserIdAndProductId(Long userId, Long productId);

    void deleteById(Long id);
}
