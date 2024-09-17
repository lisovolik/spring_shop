package com.lisovolik.spring_shop.repositories;

import com.lisovolik.spring_shop.entity.GroupProduct;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Alexandr Lisovolik on  11.09.2024
 */

public interface GroupProductRepository extends JpaRepository<GroupProduct, Long> {
}
