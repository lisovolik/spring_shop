package com.lisovolik.spring_shop.repositories;

import com.lisovolik.spring_shop.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alexandr Lisovolik on  13.09.2024
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductByIdAndDeletedFalse(Long id);

    @Query("SELECT COUNT(p) FROM Product p")
    long getCount();


    @Query(value = "select p from Product p where p.deleted=false")
    List<Product> findAllProducts(Pageable pageable);
}
