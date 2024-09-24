package com.lisovolik.spring_shop.repositories;

import com.lisovolik.spring_shop.entity.Address;
import com.lisovolik.spring_shop.entity.Product;
import com.lisovolik.spring_shop.entity.ProductPrice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alexandr Lisovolik on  24.09.2024
 */

@Repository
public interface PriceRepository extends JpaRepository<ProductPrice, Long> {
    @Query("SELECT COUNT(p) FROM ProductPrice p")
    long getCount();


    @Query(value = "select p from ProductPrice p")
    List<ProductPrice> findAllPrices(Pageable pageable);

    @Query(value = "SELECT p FROM ProductPrice p WHERE p.productId = :id ORDER BY p.createdOn DESC LIMIT 1")
    Optional<ProductPrice> findLastByPriceId(Long id);
}
