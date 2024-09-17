package com.lisovolik.spring_shop.repositories;

import com.lisovolik.spring_shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Alexandr Lisovolik on  13.09.2024
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
