package com.lisovolik.spring_shop.admin;

import com.lisovolik.spring_shop.entity.product.GroupProduct;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Alexandr Lisovolik on  11.09.2024
 */

public interface GroupProductRepository extends JpaRepository<GroupProduct, Long> {
}
