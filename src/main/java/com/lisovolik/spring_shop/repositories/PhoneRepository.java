package com.lisovolik.spring_shop.repositories;

import com.lisovolik.spring_shop.entity.Address;
import com.lisovolik.spring_shop.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Alexandr Lisovolik on  17.09.2024
 */

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    List<Phone> findPhoneByUserId(Long userId);
}
