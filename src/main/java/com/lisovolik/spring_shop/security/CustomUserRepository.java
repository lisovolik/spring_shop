package com.lisovolik.spring_shop.security;

import com.lisovolik.spring_shop.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by Alexandr Lisovolik on  10.09.2024
 */

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, String> {
    Optional<CustomUser> findByUsername(String username);

    @Modifying
    @Query("update CustomUser u set u.lastLogin=:lastLogin where u.id = :id")
    void updateLastLogin(LocalDateTime lastLogin, Long id);
}
