package com.lisovolik.spring_shop.repositories;

import com.lisovolik.spring_shop.entity.Address;
import com.lisovolik.spring_shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Alexandr Lisovolik on  19.09.2024
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserIdOrderByCreatedOnDesc(Long userId);
}
