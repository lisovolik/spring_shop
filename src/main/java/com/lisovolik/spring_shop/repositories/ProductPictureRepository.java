package com.lisovolik.spring_shop.repositories;

import com.lisovolik.spring_shop.entity.ProductPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Alexandr Lisovolik on  17.09.2024
 */

@Repository
public interface ProductPictureRepository extends JpaRepository<ProductPicture, Long> {
    void deleteByName(String name);
    Optional<ProductPicture> findByName(String name);
}
