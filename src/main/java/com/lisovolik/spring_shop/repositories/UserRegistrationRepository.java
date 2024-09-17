package com.lisovolik.spring_shop.repositories;

import com.lisovolik.spring_shop.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

public interface UserRegistrationRepository extends JpaRepository<CustomUser, Long> {
    Optional<CustomUser> findByEmail(String email);
}
